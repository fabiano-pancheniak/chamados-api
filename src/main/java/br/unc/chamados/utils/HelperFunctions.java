package br.unc.chamados.utils;
public class HelperFunctions {
    public boolean validateEmailDomain(String email) throws RuntimeException{
        // Split the email address at the "@" character
        String[] parts = email.split("@");

        // Check if the split resulted in two parts (i.e., if there's exactly one "@" in the email)
        if (parts.length == 2) {
            // Extract the domain part
            String domain = parts[1];

            // Check if the domain ends with "unc.br" or "professor.unc.br"
            if (domain.endsWith("unc.br") || domain.endsWith("professor.unc.br")) {
                return true; // Domain is valid
            }
        }
        throw new RuntimeException("Email não é @unc.br");
    }
}
