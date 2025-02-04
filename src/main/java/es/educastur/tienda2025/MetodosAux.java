package es.educastur.tienda2025;

public class MetodosAux {
          //EJEMPLO DE VALIDACIÓN DE UN DNI NACIONAL
          /* String dni;
          do {          
              System.out.println("teclea DNI:");
              dni=sc.next();
          } while(!validarDNI(dni));
          System.out.println("El dni bueno es: " + dni); */
         
  
  
      //MÉTODO PARA VALIDAR UN DNI NACIONAL
  
    public static boolean validarDni(String dni) {
          // Verificar que el DNI tiene un formato válido
          if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
              return false;
          }
          // Extraer el número y la letra del DNI
          String numeroStr = dni.substring(0, 8);
          char letra = dni.charAt(8);
  
          // Calcular la letra correspondiente al número del DNI
          char letraCalculada = calcularLetraDni(Integer.parseInt(numeroStr));
                    // Comparar la letra calculada con la letra proporcionada y devolver
                    // el resultado de la comparación TRUE/FALSE
                    return letra == letraCalculada; 
    }
        
    public static char calcularLetraDni(int numero) {
          String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
          return letras.charAt(numero % 23);
    } 

      public static boolean esInt(String s){
        int n;
        try{
            n=Integer.parseInt(s);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    public boolean esDouble(String s){
        double n;
        try{
            n=Double.parseDouble(s);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
}
