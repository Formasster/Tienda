package es.educastur.tienda2025;

public class MetodosAux {
          //EJEMPLO DE VALIDACIÓN DE UN DNI NACIONAL

          String dni;
          do {          
              System.out.println("teclea DNI:");
              dni=sc.next();
          } while(!validarDNI(dni));
          System.out.println("El dni bueno es: " + dni);
  
  
      //MÉTODO PARA VALIDAR UN DNI NACIONAL
  
      public boolean validarDNI(String dni) {
          // Verificar que el DNI tiene un formato válido
          if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
              return false;
          }
          // Extraer el número y la letra del DNI
          String numeroStr = dni.substring(0, 8);
          char letra = dni.charAt(8);
  
          // Calcular la letra correspondiente al número del DNI
          char letraCalculada = calcularLetraDNI(Integer.parseInt(numeroStr));
          // Comparar la letra calculada con la letra proporcionada y devolver
          // el resultado de la comparación TRUE/FALSE
          return letra == letraCalculada; 
      }
      public char calcularLetraDNI(int numero) {
          String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
          return letras.charAt(numero % 23);
      } 
}
