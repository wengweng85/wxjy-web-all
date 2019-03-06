package com.insigma.common.rsa;

import com.insigma.common.util.MD5Util;
import com.insigma.resolver.AppException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * RSA�㷨����/���ܹ�����
 * @author Administrator
 *
 */
public class RSAUtils {

    /** �㷨���� */
    private static final String ALGORITHOM = "RSA";

    private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";

    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtils.class);

    /** RSA_PUBLICKEY_FILENAME */
    private static final String RSA_PUBLICKEY_FILENAME = "/publicKey.keystore";

    /** RSA_PRIVATE_FILENAME */
    private static final String RSA_PRIVATE_FILENAME = "/privateKey.keystore";

    /**�������ɵ���Կ�Ե��ļ����� ���� */
    private static final String RSA_PAIR_KEYSTORE_FILENAME = "/__RSA_PAIR.keystore";

    /*Ĭ����Կ*/
    private static final String DEFUALT_PRIVATE_KEY="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIYc/OPR3MzgKZQaRfOlfK1xw36YU1KVwU96sAzlAtm+oscCNwZRP2JN5UYAbxGcPbWVdfH76Ud/mFJc73VFQA2oSUpqJCb/BEpMYVC+PK3jZHshTuKSmo65hz6DdrkIQz1fOO4IeM4iJp1VQpmjtGd2i1zn1arzlccaY1EyylfHAgMBAAECgYABs1ZlkSCqnGEKlrayWzPUgy/GaCoOTwXPey/GShUaK7emrFmEQ/14wqIYnCLMZ13E8qs3MUmI9Y455SHIK+OfBAEI4jApE+8eSJ8LsfUd60pswSKtH/2XRVMwLJej6OJqR0FCy3CaA7etj/KvdTi7uLldIZ8tSOCq2+BjKdm8IQJBAMUK2GH9Eq/KLoYJi1zDc7RZG0pHwYJdNCYLfRuRII3w2h/1gUpBHV9vbsECpxS+kkqGyW8OA6Fc3hrWqru/L9ECQQCuPd0rQhtdpT9IPmmTvuMvPx1vE/zQjujtS9oeU7oz+8We55taSH5ytsmmWWRDHjTWqIKIZWkTZG2bC49Je0wXAkAs2ikjNP458aXhcO6+MOd3mAj0QZ001Y53Uoop6kEkzjx4pePGSUgsXysw2C+8Mx0Nxdy4YNJGuuL77P10OzLhAkADp9qfELkAQvpL6rtOVT/w+tMERJgWTBlI+UFvR3RtqMehqNxSjZjRkVIzwkZfPh//rPNoJzCILqA6E4kDEqorAkA6kK/zQc9kLeAZzgmHrsrSPpJSmmfolox7zEibgwxnfs2jeeGfhzguDCocuHCfSzcZbVigW7kRNs+mzew4sYen";

    /*Ĭ�Ϲ�Կ*/
    private static final String DEFUALT_PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGHPzj0dzM4CmUGkXzpXytccN+mFNSlcFPerAM5QLZvqLHAjcGUT9iTeVGAG8RnD21lXXx++lHf5hSXO91RUANqElKaiQm/wRKTGFQvjyt42R7IU7ikpqOuYc+g3a5CEM9XzjuCHjOIiadVUKZo7Rndotc59Wq85XHGmNRMspXxwIDAQAB";


    /** ��Կ��С */
    private static final int KEY_SIZE = 1024;
    /** Ĭ�ϵİ�ȫ�����ṩ�� */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    private static KeyPairGenerator keyPairGen = null;
    private static KeyFactory keyFactory = null;
    /** �������Կ�ԡ� */
    private static KeyPair oneKeyPair = null;

    private static File rsaPairFile = null;

    //AES ����
    private static String AES_PASSWORD = "WXJYEPSOFT";

    //AES KEY
    private static String AES_KEY;

    //ʱ���-��Ч��
    private static final long TIMESTAMP=5*60*1000;

    /**
     * @FieldName: SEPARATOR
     * @FieldType: char
     * @Description: �ָ���'@'
     */
    public static final String SEPARATOR = String.valueOf((char) 64);

    static {
        try {
            keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
            keyFactory = KeyFactory.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
            //aes_key��ȡ
            AES_KEY=getAesKey();
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error(ex.getMessage());
        }
        rsaPairFile = new File(getRSAPairFilePath());

    }

    private RSAUtils() {
    }

    /**
     * ���ɲ�����RSA��Կ�ԡ�
     */
    private static synchronized KeyPair generateKeyPair() {
        try {
            keyPairGen.initialize(KEY_SIZE, new SecureRandom(DateFormatUtils.format(new Date(),"yyyyMMdd").getBytes()));
            oneKeyPair = keyPairGen.generateKeyPair();
            saveKeyPair(oneKeyPair);
            return oneKeyPair;
        } catch (InvalidParameterException ex) {
            LOGGER.error("KeyPairGenerator does not support a key length of " + KEY_SIZE + ".", ex);
        } catch (NullPointerException ex) {
            LOGGER.error("RSAUtil#KEY_PAIR_GEN is null, can not generate KeyPairGenerator instance.",ex);
        }
        return null;
    }

    /**
     * ��������/��ȡ����Կ���ļ���·����
     */
    private static String getRSAPairFilePath() {
        String urlPath = RSAUtils.class.getResource("/").getPath();
        return (new File(urlPath).getParent()+RSA_PAIR_KEYSTORE_FILENAME);
    }

    /**
     * ����Ҫ�����µ���Կ���ļ����򷵻� {@code true}������ {@code false}��
     */
    private static boolean isCreateKeyPairFile() {
        // �Ƿ񴴽��µ���Կ���ļ�
        boolean createNewKeyPair = false;
        if (!rsaPairFile.exists() || rsaPairFile.isDirectory()) {
            createNewKeyPair = true;
        }
        return createNewKeyPair;
    }

    /**
     * ��ָ����RSA��Կ�����ļ���ʽ���档
     *
     * @param keyPair Ҫ�������Կ�ԡ�
     */
    private static void saveKeyPair(KeyPair keyPair) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = FileUtils.openOutputStream(rsaPairFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(keyPair);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
            IOUtils.closeQuietly(fos);
        }
    }



    /**
     * ��ָ����RSA��Կ�����ļ���ʽ���� ����Ϊ�ı���ʽ
     * @param publicKey
     * @param privateKey
     */
    private static void saveKeyPair(String publicKey, String privateKey) {
        Writer out = null;
        try {
            out = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(new File(RSA_PUBLICKEY_FILENAME)),"GBK"));
            out.write(publicKey);
            out.close();
            out = new BufferedWriter( new OutputStreamWriter(new FileOutputStream(new File(RSA_PRIVATE_FILENAME)),"GBK"));
            out.write(privateKey);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * ����RSA��Կ�ԡ�
     */
    public static KeyPair getKeyPair() {
        // �����ж��Ƿ���Ҫ���������µ���Կ���ļ�
        if (isCreateKeyPairFile()) {
            // ֱ��ǿ��������Կ���ļ��������뻺�档
            return generateKeyPair();
        }
        if (oneKeyPair != null) {
            return oneKeyPair;
        }
        return readKeyPair();
    }

    // ͬ�������������Կ��
    public static KeyPair readKeyPair() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = FileUtils.openInputStream(rsaPairFile);
            ois = new ObjectInputStream(fis);
            oneKeyPair = (KeyPair) ois.readObject();
            return oneKeyPair;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
            IOUtils.closeQuietly(fis);
        }
        return null;
    }

    /**
     * ��ָ����RSA��Կ�����ļ���ʽ���档
     *
     * @return  keyPair �������Կ�ԡ�
     */
    public static KeyPair readKeyPair(String keyfilepath) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        KeyPair keyPair=null;
        try {
            fis = FileUtils.openInputStream(new File(keyfilepath));
            ois = new ObjectInputStream(fis);
            keyPair=(KeyPair)ois.readObject();
            return keyPair;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
            IOUtils.closeQuietly(ois);
        }
        return keyPair;
    }

    /**
     * ���ݸ�����ϵ����ר��ָ������һ��RSAר�õĹ�Կ����
     *
     * @param modulus ϵ����
     * @param publicExponent ר��ָ����
     * @return RSAר�ù�Կ����
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) {
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException ex) {
            LOGGER.error("RSAPublicKeySpec is unavailable.", ex);
        } catch (NullPointerException ex) {
            LOGGER.error("RSAUtil#KEY_FACTORY is null, can not generate KeyFactory instance.", ex);
        }
        return null;
    }

    /**
     * ���ݸ�����ϵ����ר��ָ������һ��RSAר�õ�˽Կ����
     *
     * @param modulus ϵ����
     * @param privateExponent ר��ָ����
     * @return RSAר��˽Կ����
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) {
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus),
                new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
        } catch (InvalidKeySpecException ex) {
            LOGGER.error("RSAPrivateKeySpec is unavailable.", ex);
        } catch (NullPointerException ex) {
            LOGGER.error("RSAUtil#KEY_FACTORY is null, can not generate KeyFactory instance.", ex);
        }
        return null;
    }

    /**
     * ���ݸ�����16����ϵ����ר��ָ���ַ�������һ��RSAר�õ�˽Կ����
     *
     * @param hexModulus ϵ����
     * @param hexPrivateExponent ר��ָ����
     * @return RSAר��˽Կ����
     */
    public static RSAPrivateKey getRSAPrivateKey(String hexModulus, String hexPrivateExponent) {
        if(StringUtils.isBlank(hexModulus) || StringUtils.isBlank(hexPrivateExponent)) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("hexModulus and hexPrivateExponent cannot be empty. RSAPrivateKey value is null to return.");
            }
            return null;
        }
        byte[] modulus = null;
        byte[] privateExponent = null;
        try {
            modulus = Hex.decodeHex(hexModulus.toCharArray());
            privateExponent = Hex.decodeHex(hexPrivateExponent.toCharArray());
        } catch(DecoderException ex) {
            LOGGER.error("hexModulus or hexPrivateExponent value is invalid. return null(RSAPrivateKey).");
        }
        if(modulus != null && privateExponent != null) {
            return generateRSAPrivateKey(modulus, privateExponent);
        }
        return null;
    }

    /**
     * ���ݸ�����16����ϵ����ר��ָ���ַ�������һ��RSAר�õĹ�Կ����
     *
     * @param hexModulus ϵ����
     * @param hexPublicExponent ר��ָ����
     * @return RSAר�ù�Կ����
     */
    public static RSAPublicKey getRSAPublidKey(String hexModulus, String hexPublicExponent) {
        if(StringUtils.isBlank(hexModulus) || StringUtils.isBlank(hexPublicExponent)) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("hexModulus and hexPublicExponent cannot be empty. return null(RSAPublicKey).");
            }
            return null;
        }
        byte[] modulus = null;
        byte[] publicExponent = null;
        try {
            modulus = Hex.decodeHex(hexModulus.toCharArray());
            publicExponent = Hex.decodeHex(hexPublicExponent.toCharArray());
        } catch(DecoderException ex) {
            LOGGER.error("hexModulus or hexPublicExponent value is invalid. return null(RSAPublicKey).");
        }
        if(modulus != null && publicExponent != null) {
            return generateRSAPublicKey(modulus, publicExponent);
        }
        return null;
    }

    /**
     * ʹ��ָ���Ĺ�Կ�������ݡ�
     *
     * @param publicKey �����Ĺ�Կ��
     * @param data Ҫ���ܵ����ݡ�
     * @return ���ܺ�����ݡ�
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.ENCRYPT_MODE, publicKey);
        return ci.doFinal(data);
    }

    /**
     * ʹ��ָ����˽Կ�������ݡ�
     *
     * @param privateKey ������˽Կ��
     * @param data Ҫ���ܵ����ݡ�
     * @return ԭ���ݡ�
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM, DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE, privateKey);
        return ci.doFinal(data);
    }

    /**
     * ʹ�ø����Ĺ�Կ���ܸ������ַ�����
     * <p />
     * �� {@code publicKey} Ϊ {@code null}������ {@code plaintext} Ϊ {@code null} �򷵻� {@code
     * null}��
     *
     * @param publicKey �����Ĺ�Կ��
     * @param plaintext �ַ�����
     * @return �����ַ��������ġ�
     */
    public static String encryptString(PublicKey publicKey, String plaintext) {
        if (publicKey == null || plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        try {
            byte[] en_data = encrypt(publicKey, data);
            return new String(Hex.encodeHex(en_data));
        } catch (Exception ex) {
            LOGGER.error(ex.getCause().getMessage());
        }
        return null;
    }

    /**
     * ʹ��Ĭ�ϵĹ�Կ���ܸ������ַ�����
     * <p />
     * ��{@code plaintext} Ϊ {@code null} �򷵻� {@code null}��
     *
     * @param plaintext �ַ�����
     * @return �����ַ��������ġ�
     */
    public static String encryptString(String plaintext) {
        if(plaintext == null) {
            return null;
        }
        byte[] data = plaintext.getBytes();
        KeyPair keyPair = getKeyPair();
        try {
            byte[] en_data = encrypt((RSAPublicKey)keyPair.getPublic(), data);
            return new String(Hex.encodeHex(en_data));
        } catch(NullPointerException ex) {
            LOGGER.error("keyPair cannot be null.");
        } catch(Exception ex) {
            LOGGER.error(ex.getCause().getMessage());
        }
        return null;
    }

    /**
     * ʹ�ø�����˽Կ���ܸ������ַ�����
     * <p />
     * ��˽ԿΪ {@code null}������ {@code encrypttext} Ϊ {@code null}����ַ����򷵻� {@code null}��
     * ˽Կ��ƥ��ʱ������ {@code null}��
     *
     * @param privateKey ������˽Կ��
     * @param encrypttext ���ġ�
     * @return ԭ���ַ�����
     */
    public static String decryptString(PrivateKey privateKey, String encrypttext) {
        if (privateKey == null || StringUtils.isBlank(encrypttext)) {
            return null;
        }
        try {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt(privateKey, en_data);
            return new String(data);
        } catch (Exception ex) {
            LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getCause().getMessage()));
        }
        return null;
    }

    /**
     * ʹ��Ĭ�ϵ�˽Կ���ܸ������ַ�����
     * <p />
     * ��{@code encrypttext} Ϊ {@code null}����ַ����򷵻� {@code null}��
     * ˽Կ��ƥ��ʱ������ {@code null}��
     *
     * @param encrypttext ���ġ�
     * @return ԭ���ַ�����
     */
    public static String decryptString(String encrypttext) {
        if(StringUtils.isBlank(encrypttext)) {
            return null;
        }
        KeyPair keyPair = getKeyPair();
        try {
            byte[] en_data = Hex.decodeHex(encrypttext.toCharArray());
            byte[] data = decrypt((RSAPrivateKey)keyPair.getPrivate(), en_data);
            return new String(data);
        } catch(NullPointerException ex) {
            LOGGER.error("keyPair cannot be null.");
        } catch (Exception ex) {
            LOGGER.error(String.format("\"%s\" Decryption failed. Cause: %s", encrypttext, ex.getMessage()));
        }
        return null;
    }

    /**
     * ʹ��Ĭ�ϵ�˽Կ������JS���ܣ�ʹ�ô����ṩ�Ĺ�Կ���ܣ����ַ�����
     *
     * @param encrypttext ���ġ�
     * @return {@code encrypttext} ��ԭ���ַ�����
     */
    public static String decryptStringByJs(String encrypttext) {
        String text = decryptString(encrypttext);
        if(text == null) {
            return null;
        }
        return text;
    }

    /** �����ѳ�ʼ����Ĭ�ϵĹ�Կ��*/
    public static RSAPublicKey getDefaultPublicKey() {
        KeyPair keyPair = getKeyPair();
        if(keyPair != null) {
            return (RSAPublicKey)keyPair.getPublic();
        }
        return null;
    }

    /** �����ѳ�ʼ����Ĭ�ϵ�˽Կ��*/
    public static RSAPrivateKey getDefaultPrivateKey() {
        KeyPair keyPair = getKeyPair();
        if(keyPair != null) {
            return (RSAPrivateKey)keyPair.getPrivate();
        }
        return null;
    }

    /** ����public key ��*/
    public static PublicKeyMap getPublicKeyMap() {
        PublicKeyMap publicKeyMap = new PublicKeyMap();
        RSAPublicKey rsaPublicKey = getDefaultPublicKey();
        publicKeyMap.setModulus(new String(Hex.encodeHex(rsaPublicKey.getModulus().toByteArray())));
        publicKeyMap.setExponent(new String(Hex.encodeHex(rsaPublicKey.getPublicExponent().toByteArray())));
        return publicKeyMap;
    }
    /**
     * ��publickey ���õ�map��
     * @param map
     */
    @SuppressWarnings({ "rawtypes", "unused", "unchecked" })
    public static void getPublicKeyMap(Map map) {
        PublicKeyMap publicKeyMap = new PublicKeyMap();
        RSAPublicKey rsaPublicKey = getDefaultPublicKey();
        map.put("publicKeyExponent", new String(Hex.encodeHex(rsaPublicKey.getPublicExponent().toByteArray())));
        map.put("publicKeyModulus", new String(Hex.encodeHex(rsaPublicKey.getModulus().toByteArray())));
    }

    /**
     * ��˽Կ����Ϣ��������ǩ��
     * @param data ��������
     * @param privateKey
     * ˽Կ
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // ������base64�����˽Կ
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        // ����PKCS8EncodedKeySpec����
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM ָ���ļ����㷨
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
        // ȡ˽Կ�׶���
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // ��˽Կ����Ϣ��������ǩ��
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * ��˽Կ����Ϣ��������ǩ��
     * @param data ��������
     * @param privateKey
     * ˽Կ
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, PrivateKey privateKey) throws Exception {
        // ��˽Կ����Ϣ��������ǩ��
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * У������ǩ��
     *
     * @param data ��������
     * @param publicKey ��Կ
     * @param sign ����ǩ��
     *
     * @return У��ɹ�����true ʧ�ܷ���false
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        // ������base64����Ĺ�Կ
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        // ����X509EncodedKeySpec����
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM ָ���ļ����㷨
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
        // ȡ��Կ�׶���
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // ��֤ǩ���Ƿ�����
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * У������ǩ��
     *
     * @param data ��������
     * @param publicKey ��Կ
     * @param sign ����ǩ��
     *
     * @return У��ɹ�����true ʧ�ܷ���false
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, PublicKey publicKey, String sign) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        // ��֤ǩ���Ƿ�����
        return signature.verify(Base64.decodeBase64(sign));
    }

    /**
     * ����<br>
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
        // ����Կ����
        byte[] keyBytes = Base64.decodeBase64(key);
        // ȡ��˽Կ
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // �����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        //return cipher.doFinal(data);
        byte[] enBytes = null;
        // ����ʱ����128�ֽھͱ���Ϊ�˲��÷ֶν��ܵİ취������
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * ����<br>
     *
     * @param encodedata
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encodedata, String key) throws Exception {
        return new String(decryptByPrivateKey(Base64.decodeBase64(encodedata),key));
    }

    /**
     * ����<br>
     *
     * @param encodedata
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String encodedata) throws Exception {
        return new String(decryptByPrivateKey(Base64.decodeBase64(encodedata),readPrivateKey()));
    }

    /**
     * ����<br>
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
        // �����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        //return cipher.doFinal(data);
        byte[] enBytes = null;
        // ����ʱ����128�ֽھͱ���Ϊ�˲��÷ֶν��ܵİ취������
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * ����<br>
     * ��˽Կ����
     *
     * @param encodedata
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encodedata, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return  new String(decryptByPublicKey(Base64.decodeBase64(encodedata),key));
    }

    /**
     * ����<br>
     * ��˽Կ����
     *
     * @param encodedata
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String encodedata) throws Exception {
        return  new String(decryptByPublicKey(Base64.decodeBase64(encodedata),readPublicKey()));
    }

    /**
     * ����<br>
     * ��˽Կ����
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // ����Կ����
        byte[] keyBytes = Base64.decodeBase64(key);

        // ȡ�ù�Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // �����ݽ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        // return cipher.doFinal(data);

        byte[] enBytes = null;
        // ����ʱ����128�ֽھͱ���Ϊ�˲��÷ֶν��ܵİ취������
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i += 128) {
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i, i + 128));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * ����<br>
     * �ù�Կ����
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // �Թ�Կ����
        byte[] keyBytes = Base64.decodeBase64(key);

        // ȡ�ù�Կ
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //return cipher.doFinal(data);
        // ����ʱ����117�ֽھͱ���Ϊ�˲��÷ֶμ��ܵİ취������
        byte[] enBytes = null;
        for (int i = 0; i < data.length; i += 64) {
            // ע��Ҫʹ��2�ı������������ּ��ܺ�������ٽ���ʱΪ����
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * ����<br>
     * �ù�Կ����
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return Base64.encodeBase64String(encryptByPublicKey(data.getBytes(), key));
    }

    /**
     * ����<br>
     * �ù�Կ����
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data) throws Exception {
        return Base64.encodeBase64String(encryptByPublicKey(data.getBytes(), readPublicKey()));
    }

    /**
     * ����<br>
     * �ù�Կ����
     *
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data,PublicKey publicKey) throws Exception {
        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        //return cipher.doFinal(data);
        // ����ʱ����117�ֽھͱ���Ϊ�˲��÷ֶμ��ܵİ취������
        byte[] enBytes = null;
        for (int i = 0; i < data.length; i += 64) {
            // ע��Ҫʹ��2�ı������������ּ��ܺ�������ٽ���ʱΪ����
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * ����<br>
     * ��˽Կ����
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String key) throws Exception {
        return Base64.encodeBase64String(encryptByPrivateKey(data.getBytes(), key));
    }

    /**
     * ����<br>
     * ��˽Կ����
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data) throws Exception {
        return Base64.encodeBase64String(encryptByPrivateKey(data.getBytes(), readPrivateKey()));
    }

    /**
     * ����<br>
     * ��˽Կ����
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
        // ����Կ����
        byte[] keyBytes = Base64.decodeBase64(key);

        // ȡ��˽Կ
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHOM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        //return cipher.doFinal(data);
        byte[] enBytes = null;
        for (int i = 0; i < data.length; i += 64) {
            // ע��Ҫʹ��2�ı������������ּ��ܺ�������ٽ���ʱΪ����
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * ����<br>
     * ��˽Կ����
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
        // �����ݼ���
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        //return cipher.doFinal(data);
        byte[] enBytes = null;
        for (int i = 0; i < data.length; i += 64) {
            // ע��Ҫʹ��2�ı������������ּ��ܺ�������ٽ���ʱΪ����
            byte[] doFinal = cipher.doFinal(ArrayUtils.subarray(data, i,i + 64));
            enBytes = ArrayUtils.addAll(enBytes, doFinal);
        }
        return enBytes;
    }

    /**
     * ��������/��ȡ����Կ���ļ���·����
     */
    private static String getPublicKeyfilePath() {
        String urlPath = RSAUtils.class.getResource("/").getPath();
        String filepath=new File(urlPath).getParent() + RSA_PUBLICKEY_FILENAME;
        System.out.println("key path:"+filepath);
        return filepath;
    }

    /**
     * readPrivateKey
     */
    public static String readPrivateKey() throws  Exception {
        /*try {
            BufferedReader br = new BufferedReader(new FileReader(getPrivateKeyfilePath()));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            throw new Exception("read key error:"+e.getMessage());
        }*/
        return DEFUALT_PRIVATE_KEY;
    }


    /**
     * readPublicKey
     */
    public static String readPublicKey(){
        return DEFUALT_PUBLIC_KEY;
    }

    /**
     *
     * @param keyfilepath ֤���ļ�·��
     * @return PublicKey �������Կ�ԡ�
     */
    public static String readKey(String keyfilepath) throws  Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(keyfilepath));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            throw new Exception("read key error:"+e.getMessage());
        }
    }

    /**
     * ��������/��ȡ����Կ���ļ���·����
     */
    private static String getPrivateKeyfilePath() {
        String urlPath = RSAUtils.class.getResource("/").getPath();
        String filepath=new File(urlPath).getParent() + RSA_PRIVATE_FILENAME;
        System.out.println("key path:"+filepath);
        return filepath;
    }

    /**
     * ȡ��˽Կ
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * ȡ�ù�Կ
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64.encodeBase64String(key.getEncoded());
    }

    /**
     * ��ʼ����Կ
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHOM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        // ��Կ
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // ˽Կ
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        saveKeyPair(keyPair);

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);

        String privateKeyString = RSAUtils.getPrivateKey(keyMap);
        String publicKeyString = RSAUtils.getPublicKey(keyMap);
        System.out.print("��ԿBase64����:");
        System.out.println(publicKeyString);
        System.out.print("˽ԿBase64����:");
        System.out.println(""+privateKeyString);
        saveKeyPair(publicKeyString,privateKeyString);

        return keyMap;
    }


    /**
     * �ͻ��˼��ܼ�ǩ��
     * ʹ��aes�����ݽ��м���
     * ʹ��rsa��aeskey���м��ܺ�ƴװ����
     * @param plantText ��������
     * @param publickey ��Կ
     * @return ǩ������������
     * @throws Exception
     */
    public static EncryptDataStruct encryptByAesAndRsaPublickey(String plantText,String publickey) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        //�ͻ��˶�̬����aeskey
        String client_aes_key=getAesKey();
        RSAUtils.EncryptDataStruct encryptDataStruct=new RSAUtils.EncryptDataStruct();
        //����
        String aes_str = AESCBCUtils.encrypt(plantText, client_aes_key); //aes����
        String rsa_str = RSAUtils.encryptByPublicKey(client_aes_key,publickey); //rsa����--��Կ����
        StringBuffer sb = new StringBuffer();
        long timestamp=System.currentTimeMillis()+TIMESTAMP;
        sb.append(aes_str).append(RSAUtils.SEPARATOR).append(rsa_str).append(RSAUtils.SEPARATOR).append(timestamp);//��ʱ���
        String data= sb.toString();
        //������ǩ��=�ı�+��ǰʱ���
        String sign= MD5Util.MD5Encode(plantText+timestamp);
        encryptDataStruct.setData(data);
        encryptDataStruct.setSign(sign);
        return encryptDataStruct;
    }


    /**
     * �ͻ��˼��ܼ�ǩ��
     * ʹ��aes�����ݽ��м���
     * ʹ��rsa��aeskey���м��ܺ�ƴװ����
     * @param plantText ��������
     * @return ǩ������������
     * @throws Exception
     */
    public static EncryptDataStruct encryptByAesAndRsaPublickey(String plantText) throws Exception{
        return encryptByAesAndRsaPublickey(plantText,DEFUALT_PUBLIC_KEY);
    }
    /**
     * ����˼���
     * ʹ��aes�����ݽ��м���
     * ʹ��rsa��aeskey���м��ܺ�ƴװ����
     * @param plantText
     * @privatekey
     * @return
     * @throws Exception
     */
    public static String encryptByAesAndRsaPrivateKey(String plantText,String privatekey) throws Exception{
        //����˶�̬����aeskey
        String server_aes_key=getAesKey();
        String aes_str = AESCBCUtils.encrypt(plantText, server_aes_key); //aes����
        String rsa_str = RSAUtils.encryptByPrivateKey(server_aes_key,privatekey); //rsa����--��Կ����
        StringBuffer sb = new StringBuffer();
        sb.append(aes_str).append(RSAUtils.SEPARATOR).append(rsa_str);
        return sb.toString();
    }

    /**
     * ����˼���
     * ʹ��aes�����ݽ��м���
     * ʹ��rsa��aeskey���м��ܺ�ƴװ����
     * @param plantText
     * @privatekey
     * @return
     * @throws Exception
     */
    public static String encryptByAesAndRsaPrivateKey(String plantText) throws Exception{
        return encryptByAesAndRsaPrivateKey(plantText,DEFUALT_PRIVATE_KEY);
    }




    /**
     * ����˽��ܼ���ǩ
     * ʹ��rsa��aeskey���н��ܳ�aes_key
     * ʹ��aes�����ݽ��н���
     * @param encryptDataStruct //����򷵻���������
     * @param privatekey
     * @return
     * @throws Exception
     */
    public static  String decryptByAesAndRsaPrivateKey(EncryptDataStruct encryptDataStruct, String privatekey) throws Exception{
        //У�鱨�ĸ�ʽ
        String[] dataArr = encryptDataStruct.getData().split(RSAUtils.SEPARATOR);
        if(dataArr.length != 3){
            throw new AppException("api request data is incorrect,we need 3 param");
        }
        long timestamp=Long.parseLong(dataArr[2]);
        //����ӿ�ʱ���Ƿ����
        Date nowTime = new Date(System.currentTimeMillis());
        Date timestamp_time= new Date(timestamp);
        //System.out.println("nowTime="+nowTime.toString());
        //System.out.println("timestamp_time="+timestamp_time.toString());
        if(timestamp_time.after(nowTime)){
            //AES���ģ�AES(P)
            String aesCipherText = dataArr[0];
            //RSA���ģ�RSA(AES_KEY)
            String rsaCipherText = dataArr[1];

            String aesKey = RSAUtils.decryptByPrivateKey(rsaCipherText,privatekey);//rsa����--˽Կ����
            //System.out.println("AES key :" + aesKey);
            String reqparams = AESCBCUtils.decrypt(aesCipherText, aesKey).trim();//aes����--��������

            //System.out.println("server reqparams:" + reqparams);
            String sign=MD5Util.MD5Encode(reqparams+timestamp);
            //System.out.println("server sign:" + sign);

            //�ж�ǩ���Ƿ���ȷ
            if(sign.equals(encryptDataStruct.getSign())) {
                //System.out.println("����ǩ����֤�ɹ���");
                //System.out.println("���ܺ����������Ϊ:" + reqparams);
                return reqparams;
            }
            else{
                throw new Exception("sign error");
            }
        }else{
            throw new AppException("timestamp is out of date,api request is not valid");
        }

    }

    /**
     * ����˽��ܼ���ǩ
     * ʹ��rsa��aeskey���н��ܳ�aes_key
     * ʹ��aes�����ݽ��н���
     * @param encryptDataStruct
     * @return
     * @throws Exception
     */
    public static  String decryptByAesAndRsaPrivateKey(EncryptDataStruct encryptDataStruct) throws Exception{
        return decryptByAesAndRsaPrivateKey(encryptDataStruct,DEFUALT_PRIVATE_KEY);
    }

    /**
     * ����˽���
     * ʹ��rsa��aeskey���н��ܳ�aes_key
     * ʹ��aes�����ݽ��н���
     * @param data  ��������
     * @param sign  ǩ������
     * @return
     * @throws Exception
     */
    public static  String decryptByAesAndRsaPrivateKey(String data,String sign) throws Exception{
        RSAUtils.EncryptDataStruct encryptDataStruct=new RSAUtils.EncryptDataStruct();
        encryptDataStruct.setData(data);
        encryptDataStruct.setSign(sign);
        return decryptByAesAndRsaPrivateKey(encryptDataStruct,DEFUALT_PRIVATE_KEY);
    }


    /**
     * �ͻ��˽���
     * ʹ��rsa��aeskey���н��ܳ�aes_key
     * ʹ��aes�����ݽ��н���
     * @param responseParams ���ص���������
     * @param publickey
     * @return
     * @throws Exception
     */
    public static String decryptByAesAndRsaPublickey(String responseParams,String publickey) throws AppException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        if(null!=responseParams&&!responseParams.equals("")){
            //У�鱨�ĸ�ʽ
            String[] dataArr = responseParams.split(RSAUtils.SEPARATOR);
            if(dataArr.length != 2){
                throw new AppException("api request data is incorrect,we need 2 param");
            }
            //AES���ģ�AES(P)
            String aesCipherText = dataArr[0];
            //RSA���ģ�RSA(AES_KEY)
            String rsaCipherText = dataArr[1];
            String aesKey = RSAUtils.decryptByPublicKey(rsaCipherText,publickey);//rsa����--˽Կ����
            //System.out.println("AES��ԿΪ:" + aesKey);
            String reqparams = AESCBCUtils.decrypt(aesCipherText, aesKey).trim();//aes����--��������
            //System.out.println("���ܺ����������Ϊ:" + reqparams);
            return reqparams;
        }
        return responseParams;
    }



    /**
     * �ͻ��˽���
     * ʹ��rsa��aeskey���н��ܳ�aes_key
     * ʹ��aes�����ݽ��н���
     * @param responseParams ���ص���������
     * @return
     * @throws Exception
     */
    public static String decryptByAesAndRsaPublickey(String responseParams) throws Exception{
        return decryptByAesAndRsaPublickey(responseParams,DEFUALT_PUBLIC_KEY);
    }


    /**
     * ���������Կ
     */
    private static String getAesKey() throws  NoSuchAlgorithmException{
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);//Ҫ���ɶ���λ��ֻ��Ҫ�޸����Ｔ��128, 192��256
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        String s = byteToHexString(b);
        //s="8NONwyJtHesysWpM";
        //System.out.println("aes="+s);
        return s;
    }

    /**
     * ʹ��ָ�����ַ���������Կ
     */
    private static String getAesKeyByPass() throws NoSuchAlgorithmException{
        //������Կ
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        // kg.init(128);//Ҫ���ɶ���λ��ֻ��Ҫ�޸����Ｔ��128, 192��256
        //SecureRandom�����ɰ�ȫ��������У�password.getBytes()�����ӣ�ֻҪ������ͬ�����о�һ�����������ɵ���Կ��һ����
        kg.init(128, new SecureRandom(AES_PASSWORD.getBytes()));
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        String s = byteToHexString(b);
        //System.out.println(s);
        //System.out.println("ʮ��������Կ����Ϊ"+s.length());
        //System.out.println("��������Կ�ĳ���Ϊ"+s.length()*4);
        System.out.println("aes="+s);
        return s;

    }
    /**
     * byte����ת��Ϊ16�����ַ���
     * @param bytes
     * @return
     */
    private static String byteToHexString(byte[] bytes){
        String hs = "";
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase().substring(0,16);
    }

    /**
     * Created by wengsh
     * �������ݸ�ʽ
     * {
     *     data:'����rsa�Լ�aes�㷨����', rsa�Լ�aes���ܴ���@���ŷָ�����aes_key_encode@rsa_key_encode
     *     sign:'����md5�㷨ǩ��'
     * }
     *
     */
    public static class EncryptDataStruct {

        public EncryptDataStruct(){

        }
        private String data;
        private String sign;



        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }


}