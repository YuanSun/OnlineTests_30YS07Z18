package com.acquisio.basic.java.question08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.internal.Lists;

/**
 * QUESTION 09: Lambdas (https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html)
 * Using JDK8 Lambdas, add the code to transform the input file to apply those rules.
 * 1- Filter out lines where the amount is lower than 50$
 * 2- Add a Taxes column right after the Amount column, which is 15% of the Amount.
 * 3- Add a Total column right after the Taxes column, which is the sum of Amount and Taxes.
 * 4- Remove the ShoppingCartTitle columns.
 * <p>
 * The input file contains those columns
 * User,Amount,ShoppingCartTitle,NbItems
 * <p>
 * IMPORTANT: Add all missing javadoc and/or unit tests that you think is necessary.
 */
public class Lambdas {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Lambdas instance = new Lambdas();
        File input = new File(Thread.currentThread().getContextClassLoader().getResource("./carts.csv").toURI());
        File output = new File("./carts_output.csv");
        output.delete();

        instance.convertCarts(input, output);

        if (output.exists()) {
            Files.lines(output.toPath()).forEachOrdered(line -> System.out.println(line));
        }
    }

    void convertCarts(File input, File output) throws IOException {
        // TODO: Insert your code here.
      final Double TAXRATE = 0.15;
      final String SEPARATOR = ",";
      List<List<String>> inputList = new ArrayList<>();
      
      // ==== Read file ====
      InputStream is = new FileInputStream(input); // already thrown exception
      try(
      BufferedReader br = new BufferedReader(new InputStreamReader(is))){
        inputList = br.lines()
            .filter(line -> {
            // filter out amount < 50
            String[] str = line.split(SEPARATOR);
            Double amt = Double.parseDouble(str[1]);
            return amt.compareTo(50.0) > 0;
          })
            .map(line -> {
            // Add taxes column, add sum column and remove shoppingCartTitle column
            String[] str = line.split(SEPARATOR);
            List<String> lineArr = new ArrayList<>();
            for (String row : str) {
              lineArr.add(row);
            }
            Double tax = Double.parseDouble(lineArr.get(1)) * TAXRATE;
            Double sum = Double.parseDouble(lineArr.get(1)) + tax;
            lineArr.add(2, String.valueOf(tax));
            lineArr.add(3, String.valueOf(sum));
            lineArr.remove(4);
            return lineArr;
          })
            .collect(Collectors.toList());
      } catch (Exception e) {
        e.printStackTrace();
      };
      
      // ==== Output file ===== 
   
      StringBuilder sb = new StringBuilder();
      
      // prepare string builder
      inputList.stream().forEach(row -> {
        row.stream().forEach(col -> {
          sb.append(col).append(SEPARATOR);
        });
        // Remove last separator
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
      });
      
      // write to file
      PrintWriter pw = new PrintWriter(output);
      pw.write(sb.toString());
      pw.close();
    }

}
