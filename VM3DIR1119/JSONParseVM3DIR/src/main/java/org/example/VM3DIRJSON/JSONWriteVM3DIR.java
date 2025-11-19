package org.example.VM3DIRJSON;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class JSONWriteVM3DIR {

    public static void main(String[] args) {
        try (FileReader reader = new FileReader("src/orarendVM3DIR.json")) {
            // JSON fájl beolvasása és parse-olása
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            // VM3DIR_orarend objektum kinyerése
            JSONObject orarend = (JSONObject) jsonObject.get("VM3DIR_orarend");

            // ora tömb kinyerése
            JSONArray oraArray = (JSONArray) orarend.get("ora");

            // Konzolra írás blokk formátumban
            System.out.println("=== ÓRAREND ADATOK (BLOKK FORMÁTUM) ===\n");
            System.out.println("VM3DIR_orarend:");
            System.out.println("ora:");

            for (Object ora : oraArray) {
                JSONObject oraObj = (JSONObject) ora;

                System.out.println("----");
                System.out.println("targy: " + oraObj.get("targy"));

                JSONObject idopont = (JSONObject) oraObj.get("idopont");
                System.out.println("idopont:");
                System.out.println("nap: " + idopont.get("nap"));
                System.out.println("tol: " + idopont.get("tol"));
                System.out.println("ig: " + idopont.get("ig"));

                System.out.println("helyszin: " + oraObj.get("helyszin"));
                System.out.println("oktato: " + oraObj.get("oktato"));
                System.out.println("szak: " + oraObj.get("szak"));
                System.out.println();
            }

            // Fájlba írás formázott JSON-ként
            try (FileWriter writer = new FileWriter("src/orarendVM3DIR1.json")) {
                String formattedJson = formatJSON(jsonObject, 0);
                writer.write(formattedJson);
                System.out.println("\nJSON adatok kiírva a fájlba: orarendVM3DIR1.json");
            } catch (IOException e) {
                System.err.println("Hiba a fájl írása során: " + e.getMessage());
                e.printStackTrace();
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

    // Helper metódus a JSON szép formázásához
    private static String formatJSON(Object obj, int indent) {
        StringBuilder sb = new StringBuilder();
        String indentStr = getIndent(indent);

        if (obj instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) obj;
            sb.append("{\n");
            boolean first = true;

            for (Object key : jsonObj.keySet()) {
                if (!first) {
                    sb.append(",\n");
                }
                first = false;
                sb.append(indentStr).append("  \"").append(key).append("\": ");
                Object value = jsonObj.get(key);

                // Speciális kezelés az idopont objektumhoz
                if (value instanceof JSONObject && key.equals("idopont")) {
                    sb.append(formatIdopont((JSONObject) value, indent + 1));
                } else if (value instanceof JSONObject || value instanceof JSONArray) {
                    sb.append(formatJSON(value, indent + 1));
                } else if (value instanceof String) {
                    sb.append("\"").append(escapeString((String) value)).append("\"");
                } else {
                    sb.append(value);
                }
            }
            sb.append("\n").append(indentStr).append("}");
        } else if (obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;
            sb.append("[\n");
            for (int i = 0; i < jsonArray.size(); i++) {
                if (i > 0) {
                    sb.append(",\n");
                }
                sb.append(indentStr).append("  ");
                Object item = jsonArray.get(i);
                if (item instanceof JSONObject || item instanceof JSONArray) {
                    sb.append(formatJSON(item, indent + 1));
                } else if (item instanceof String) {
                    sb.append("\"").append(escapeString((String) item)).append("\"");
                } else {
                    sb.append(item);
                }
            }
            sb.append("\n").append(indentStr).append("]");
        }

        return sb.toString();
    }

    // Speciális formázás az idopont objektumhoz (nap, tol, ig sorrendben)
    private static String formatIdopont(JSONObject idopont, int indent) {
        StringBuilder sb = new StringBuilder();
        String indentStr = getIndent(indent);
        sb.append("{\n");

        // Nap
        sb.append(indentStr).append("  \"nap\": ");
        if (idopont.get("nap") instanceof String) {
            sb.append("\"").append(escapeString((String) idopont.get("nap"))).append("\"");
        } else {
            sb.append(idopont.get("nap"));
        }
        sb.append(",\n");

        // Tol
        sb.append(indentStr).append("  \"tol\": ").append(idopont.get("tol")).append(",\n");

        // Ig
        sb.append(indentStr).append("  \"ig\": ").append(idopont.get("ig"));

        sb.append("\n").append(indentStr).append("}");
        return sb.toString();
    }

    // Indent string generálása
    private static String getIndent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    // String escape karakterek kezelése
    private static String escapeString(String str) {
        return str.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}