package lab.entreprise.employe;

import java.io.*;
import java.util.StringTokenizer;


public class Commercial extends Employe {

    private double chiffreAffaires;
    private double sommeFixe;

    public Commercial(String nom) {
        super(nom);
    }

    public Commercial(String nom, double chiffreAffaires, double sommeFixe) {
        super(nom);
        this.chiffreAffaires = chiffreAffaires;
        this.sommeFixe = sommeFixe;
    }

    @Override
    public double getSalaire() {
        return sommeFixe + (chiffreAffaires * 0.01);
    }

    public void setInfoSalaire(double chiffreAffaires, double sommeFixe) {
        this.chiffreAffaires = chiffreAffaires;
        this.sommeFixe = sommeFixe;
    }

    public void saveToFile(FileWriter fileWriter) {
        try {
            fileWriter.write(this.getNom() + "|" + this.chiffreAffaires + "|" + this.sommeFixe + "|" + this.getSalaire());
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveToFileBinary(DataOutputStream out) {
        try {
            out.writeBytes(this.getNom() + "|" + this.chiffreAffaires + "|" + this.sommeFixe + "|" + this.getSalaire());
            out.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile(FileReader fileReader) throws IOException {
        int charCode;
        String text = "";

        while((charCode = fileReader.read()) != -1) {
            text += (char)charCode;
        }

        StringTokenizer tokenizer = new StringTokenizer(text, "|");
        Commercial commercial2 = new Commercial(tokenizer.nextToken().trim());
        commercial2.setInfoSalaire(Double.parseDouble(tokenizer.nextToken()), Double.parseDouble(tokenizer.nextToken()));
        System.out.println(commercial2);

        fileReader.close();
    }

    public void readFromFileBinary(DataInputStream in5) throws IOException {
        BufferedReader in5br = new BufferedReader(new InputStreamReader(in5));
        String text = in5br.readLine();

        StringTokenizer tokenizer = new StringTokenizer(text, "|");
        Commercial commercial2 = new Commercial(tokenizer.nextToken().trim());
        commercial2.setInfoSalaire(Double.parseDouble(tokenizer.nextToken()), Double.parseDouble(tokenizer.nextToken()));
        System.out.println(commercial2);

        in5.close();
    }

    public double getChiffreAffaires() {
        return chiffreAffaires;
    }
}
