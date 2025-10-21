package Modelo.Statics;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CodPassword {

    public static String codificarPassword(String passwordRecibida) throws RuntimeException{
        try {
            /// instanciamos el algoritmo de codificacion
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            /// se convierte a bytes la clave codificada
            byte[] hashBytes = md.digest(passwordRecibida.getBytes());

            /// Convertimos los bytes a hexadecimal
            StringBuilder claveFinal = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b); // enmascaro el byte para convertirlo sin signo
                if (hex.length() == 1) claveFinal.append('0'); // si solo tiene un digito le agrego un cero adelante
                claveFinal.append(hex);
            }

            return claveFinal.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}
