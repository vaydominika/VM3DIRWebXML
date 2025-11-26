package org.example.VM3DIRJSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

class JSONReadVM3DIR {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/orarendVM3DIR.json")) {
            // JSON fájl parse-olása
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // VM3DIR_orarend objektum kinyerése
            JSONObject orarend = (JSONObject) jsonObject.get("VM3DIR_orarend");

            // ora tömb kinyerése
            JSONArray oraArray = (JSONArray) orarend.get("ora");

            System.out.println("=== ÓRAREND ADATOK ===\n");

            // Minden óra feldolgozása
            for (Object ora : oraArray) {
                JSONObject oraObj = (JSONObject) ora;

                System.out.println("--- Óra ---");

                // Tárgy
                String targy = (String) oraObj.get("targy");
                System.out.println("targy: " + targy);

                // Időpont objektum
                JSONObject idopont = (JSONObject) oraObj.get("idopont");
                String nap = (String) idopont.get("nap");
                Long tol = (Long) idopont.get("tol");
                Long ig = (Long) idopont.get("ig");
                System.out.println("idopont:");
                System.out.println("  nap: " + nap);
                System.out.println("  tol: " + tol);
                System.out.println("  ig: " + ig);

                // Helyszín
                String helyszin = (String) oraObj.get("helyszin");
                System.out.println("helyszin: " + helyszin);

                // Oktató
                String oktato = (String) oraObj.get("oktato");
                System.out.println("oktato: " + oktato);

                // Szak
                String szak = (String) oraObj.get("szak");
                System.out.println("szak: " + szak);

                System.out.println();
            }

        } catch (IOException e) {
            System.err.println("Hiba a fájl olvasása során: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Hiba a JSON parse-olása során: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Váratlan hiba: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
