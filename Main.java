import java.io.BufferedReader;
import java.ioFileReader;


public class main {
    public static void main(String[] args) {
        String filePath = "dados.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
              
                // Ignorar a primeira linha se for cabeçalho
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Dividir a linha em colunas
                String[] columns = line.split(",");
                int key = Integer.parseInt(columns[0]); // Coluna 1: Chave
                String value = columns[1]; // Coluna 2: Valor
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
