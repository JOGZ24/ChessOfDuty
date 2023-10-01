package chess;

import java.util.Scanner;

public class Player {

    private static int autoInc; // Compteur de joueurs
    private final String NAME; // Nom du joueur
    private Team team; // Équipe du joueur

    private static final int START_PM = 0;
    private static final int MAX_PM = 100;
    private int pm;

    /**
     * Constructeur avec nom et équipe spécifiés.
     *
     * @param name Le nom du joueur.
     * @param team L'équipe du joueur.
     */
    public Player(String name, Team team) {
        this.NAME = name;
        this.team = team;
        this.pm = START_PM;
        autoInc++; // Incrémentation du compteur de joueurs
    }

    /**
     * Constructeur avec équipe spécifiée et nom demandé à l'utilisateur.
     *
     * @param team L'équipe du joueur.
     */
    public Player(Team team) {
        this(askName(), team); // Appel du constructeur avec le nom demandé à l'utilisateur
    }

    /**
     * Demande le nom du joueur à l'utilisateur.
     *
     * @return Le nom du joueur.
     */
    private static String askName() {
        Scanner sc = new Scanner(System.in);
        String name = "";
        while (isPlayerNameValid(name)) {
            System.out.println("Quel est le nom du joueur " + (autoInc + 1) + "?");
            name = sc.nextLine();
        }
        sc.close();
        return name;
    }

    /**
     * Vérifie si le nom du joueur est valide.
     *
     * @param name Le nom à vérifier.
     * @return true si le nom est valide, false sinon.
     */
    private static boolean isPlayerNameValid(String name) {
        if (!(3 <= name.length() && name.length() <= 10)) {
            return false; // Le nom doit avoir entre 3 et 10 caractères
        }
        if (name.contains(" ") || name.equals("")) {
            return false; // Le nom ne doit pas contenir d'espaces
        }
        return true;
    }

    /**
     * Retourne le nom du joueur.
     *
     * @return Le nom du joueur.
     */
    public String getNAME() {
        return NAME; // Retourne le nom du joueur
    }

    /**
     * Retourne l'équipe du joueur.
     *
     * @return L'équipe du joueur.
     */
    public Team getTeam() {
        return team; // Retourne l'équipe du joueur
    }

    /**
     * Réinitialise le compteur de joueurs.
     */
    static public void resetPlayerCount() {
        autoInc = 0; // Réinitialisation du compteur de joueurs
    }

    /**
     * Ajoute les points de magie au joueur.
     */
    static public int addPm(Player player, int additionalPm) {
        int somme = additionalPm + player.pm;

        if (MAX_PM < somme) {
            player.setPm(MAX_PM);
        } else if (somme < 0) {
            player.setPm(0);
        } else {
            player.setPm(somme);
        }

        return somme - player.pm;
    }

    /**
     * Ajoute les points de magie du joueur.
     */
    public int addPm(int additionalPm) {
        return addPm(this, additionalPm);
    }

    /**
     * Set les points de magie du joueur.
     */
    private void setPm(int pm) {
        this.pm = pm;
    }

    public boolean canUsePm(int pm) {
        return (this.pm - pm < 0) ? false : true;
    }
}