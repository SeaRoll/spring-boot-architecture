package com.yohan.service.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

public class SSLUtils {
  public static SSLSocketFactory getSocketFactory(
      final String caCrtFile, final String crtFile, final String keyFile, final String password)
      throws Exception {
    Security.addProvider(new BouncyCastleProvider());

    // load CA certificate
    X509Certificate caCert = null;

    FileInputStream fis = new FileInputStream(caCrtFile);
    BufferedInputStream bis = new BufferedInputStream(fis);
    CertificateFactory cf = CertificateFactory.getInstance("X.509");

    while (bis.available() > 0) {
      caCert = (X509Certificate) cf.generateCertificate(bis);
    }

    // load client certificate
    bis = new BufferedInputStream(new FileInputStream(crtFile));
    X509Certificate cert = null;
    while (bis.available() > 0) {
      cert = (X509Certificate) cf.generateCertificate(bis);
    }

    // load client private key
    PEMParser pemParser = new PEMParser(new FileReader(keyFile));
    Object object = pemParser.readObject();
    JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
    PrivateKey privateKey = null;
    if (object instanceof PEMEncryptedKeyPair encryptedKeyPair) {
      PEMDecryptorProvider decProv =
          new JcePEMDecryptorProviderBuilder().build(password.toCharArray());
      KeyPair keyPair = converter.getKeyPair((encryptedKeyPair).decryptKeyPair(decProv));
      privateKey = keyPair.getPrivate();
    } else if (object instanceof PEMKeyPair pemKeyPair) {
      KeyPair keyPair = converter.getKeyPair(pemKeyPair);
      privateKey = keyPair.getPrivate();
    } else if (object instanceof PrivateKeyInfo privateKeyInfo) {
      privateKey = converter.getPrivateKey(privateKeyInfo);
    }

    pemParser.close();

    // CA certificate is used to authenticate server
    KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
    caKs.load(null, null);
    caKs.setCertificateEntry("ca-certificate", caCert);
    TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509");
    tmf.init(caKs);

    // client key and certificates are sent to server so it can authenticate
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    ks.load(null, null);
    ks.setCertificateEntry("certificate", cert);
    ks.setKeyEntry(
        "private-key",
        privateKey,
        password.toCharArray(),
        new java.security.cert.Certificate[] {cert});
    KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
    kmf.init(ks, password.toCharArray());

    // finally, create SSL socket factory
    SSLContext context = SSLContext.getInstance("TLSv1.2");
    context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

    return context.getSocketFactory();
  }
}
