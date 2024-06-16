package application;

import entities.Employes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        String path = "C:\\temp\\in-stream.txt";

        System.out.println("Enter salary:");
        double salary = scanner.nextDouble();

        try (BufferedReader bf = new BufferedReader(new FileReader(path))){

            List<Employes> employes = new ArrayList<>();// criando uma lista de funcionarios
            String line = bf.readLine();

            while (line != null){
                String[] fields = line.split(",");// separa as linhas que estiverem na virgula e armazena no array.
                employes.add(new Employes(fields[0],fields[1],Double.parseDouble(fields[2]))); //instanciei um objeto do tipo Employes e adicionei ao construtor ();
                line = bf.readLine(); //le o proximo campo abaixo do aquivo de leitura
            }

            List<String> emailOrderAlfa = employes.stream()
                    .filter(e->e.getSalary() > salary )
                    .map(Employes::getEmail)
                    .sorted()//ordem alfabetico
                    .collect(Collectors.toList());

            double totalSalary = employes.stream()
                    .filter(e-> e.getName().startsWith("M")) //filtrando os nomes que começam com M
                    .mapToDouble(Employes::getSalary)// pegando o salario
                    .sum();//metodo que soma os salarios


            System.out.printf("Email of people whose salary is more than %.2f\n", salary);
            for (String p : emailOrderAlfa){
                System.out.println(p);
            }

            System.out.println("Sum of salary of people whose name starts with 'M': "+totalSalary);










        }catch (IOException e){
            System.out.println("Erro na leitura do arquivo, veja se o caminho não esta errado!"+ e.getMessage());

        }



        scanner.close();

    }
}
