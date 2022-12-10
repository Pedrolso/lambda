package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        //c:\Users\Pedro\IdeaProjects\in.txt
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Caminho do arquivo: ");
        String path = sc.nextLine();
        System.out.print("Salario: ");
        double salary = sc.nextDouble();

        System.out.println("Salary more than 2000.00");

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> names = list.stream()
                    .filter(p -> p.getSalary() > salary)
                    .map(p -> p.getEmail())
                    .sorted(comp)
                    .collect(Collectors.toList());

            names.forEach(System.out::println);

            double sum = list.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(x -> x.getSalary())
                    .reduce(0.0, (x, y) -> x + y); //reduce soma todos os itens da lista

            System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}