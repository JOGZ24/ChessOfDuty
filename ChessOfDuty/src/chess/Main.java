package chess;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import chess.errors.ImpossibleMove;
import chess.errors.IncompatinleTeam;
import chess.pieces.Piece;

public class Main {

    public static String filreadString(File path) {
        String output = "";

        try {
            FileReader fl = new FileReader(path);
            int buffer;
            while ((buffer = fl.read()) != -1) {
                output += (char) buffer;
            }
            fl.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return output;

    }

    public static String filreadString(String filename) {
        return filreadString(getFileWithName(filename));
    }

    public static File getFileWithName(String file) {
        return new File("./src/chess/textfiles/" + file);
    }

    public void showTitle() { // print un ascii art du titre
        System.out.println(filreadString("title"));
    }

    public static Player[] PlayerCreation() { // initie la création des joueurs
        Player[] joueurs = new Player[2];
        System.out.println("Nous allons définir les joueurs");
        Scanner sc = new Scanner(System.in);
        System.out.println("Joueur 1 : ");
        String joueur1 = sc.nextLine();
        System.out.println("Joueur 2 : ");
        String joueur2 = sc.nextLine();
        joueurs[0] = new Player(joueur1, Team.WHITE);
        joueurs[1] = new Player(joueur2, Team.BLACK);

        return joueurs;

    }

    public void Menu() { // Affiche le menu de choix pour jouer, quitter ou voir les crédits.

        Scanner sc = new Scanner(System.in);
        String choix = "";
        int val = 0;
        System.out.println(filreadString("menu"));

        while (val < 1 || val > 3) {
            System.out.println(
                    "Que souhaitez vous faire ? Entrez le numéro du choix souhaité (ex: Pour jouer, entrez 1.)");

            choix = sc.nextLine();
            val = Integer.parseInt(choix);
            
        }

        if (val == 1) {
            // lancer le jeu
        } else if (val == 2) {
            // lancer les crédits
        } else if (val == 3) {
            // quitter
        }
    }

    public static void credits() {
        Scanner sc = new Scanner(System.in);
        System.out.println(filreadString("credits"));

        sc.nextLine();
        

    }

    public static boolean lose(Plateau plateau, Player player)
    {
        if(plateau.getKing(player.getTeam()) == null)
        {
            return true;
        }
        return false;
        // return plateau.getKing(player.getTeam()).checkMate(plateau);
    }

    public static String alignCenter(String chaine, int taille) {
        String res = "Erreur Taille";
        if (chaine.length() < taille) {
            if ((chaine.length() % 2 != 0) != (taille % 2 != 0))
                chaine = chaine + " ";
            res = chaine;
            for (int i = 0; i < (taille - chaine.length()) / 2; i++)
                res = " " + res + " ";
        } else
            return chaine;
        return res;
    }

    public static void display(Plateau plateau) {
        final String ESPACEMENT = "   ";
        final String FONT_COLOR = "\033[0;33m";
        final String MAP_COLOR = "\033[0;34m";
        
        System.out.println(
                ESPACEMENT + alignCenter("Au Tour de " + plateau.getCurrentplayer().getNAME(), plateau.getsizeX() * 3) + "\n");
        System.out.print(ESPACEMENT);




        for (int i = 0; i < plateau.getsizeX(); i++) {
            System.out.print(" " + FONT_COLOR + (char) (i + 'A') + " ");
        }
        System.out.print("\n" + ESPACEMENT + MAP_COLOR + "╭");
        for (int colonne = 0; colonne < plateau.getsizeX() - 1; colonne++) {
            System.out.print("──┬");
        }
        System.out.print("──╮\n");

        for (int ligne = 0; ligne < plateau.getsizeX() - 1; ligne++) {
            System.out.print(" " + FONT_COLOR + (ligne + 1) + MAP_COLOR + " ");
            for (int colonne = 0; colonne < plateau.getsizeX(); colonne++) {
                Piece piece = plateau.getPiece(ligne, colonne);
                if (piece == null) {
                    System.out.print(MAP_COLOR + "│  ");
                } else {
                    System.out.print(MAP_COLOR + "│" + plateau.getPiece(ligne, colonne) + " ");
                }

            }
            System.out.print(MAP_COLOR + "│\n" + ESPACEMENT + "├");
            for (int colonne = 0; colonne < plateau.getsizeX() - 1; colonne++) {
                System.out.print("──┼");
            }
            System.out.println("──┤");
        }

        System.out.print(" " + FONT_COLOR + (plateau.getsizeX()) + MAP_COLOR + " ");
        for (int colonne = 0; colonne < plateau.getsizeX(); colonne++) {
            Piece piece = plateau.getPiece(plateau.getSizeY() - 1, colonne);
            if (piece == null) {
                System.out.print(MAP_COLOR + "│  ");
            } else {
                System.out.print(MAP_COLOR + "│" + plateau.getPiece(plateau.getSizeY() - 1, colonne) + " ");
            }

        }
        System.out.print(MAP_COLOR + "│\n" + ESPACEMENT + "╰");
        for (int colonne = 0; colonne < plateau.getsizeX() - 1; colonne++) {
            System.out.print("──┴");
        }
        System.out.print("──╯\n\033[0m");
    }


    public static void jeu() throws ImpossibleMove, IncompatinleTeam, IOException {
        clearScreen();
        Player[] players = PlayerCreation();
        Plateau plateau = new Plateau(players[0], players[1]);
        Player currentPlayer = plateau.getCurrentplayer();
        Piece pion;
        String pos;
        Scanner sc = new Scanner(System.in);
        Scanner deplacement = null;
        plateau.initialize();
        boolean deplace;
        clearScreen();
        while (!lose(plateau, currentPlayer)) {
            deplace = false;
            
            display(plateau);
            System.out.print("Choisissez un pion : ");
            
            try {
                pos = sc.nextLine().toUpperCase();
                pion = plateau.getPiece((pos.charAt(1) % 48) - 1, pos.charAt(0) % 65, currentPlayer.getTeam());
                do {
                    System.out.print("Choisissez une coordonnée de déplacement : ");
                    deplacement = new Scanner(System.in);
                    try {
                        pos = deplacement.nextLine().toUpperCase();

                        if (pos.equals("RETOUR")) {
                            deplace = true;
                        } else if (pion.canMove((pos.charAt(1) % 48) - 1, pos.charAt(0) % 65, plateau)) {
                            plateau.move(pion.getPosLigne(), pion.getPosColonne(), (pos.charAt(1) % 48) - 1,pos.charAt(0) % 65);
                            deplace = true;
                            currentPlayer = plateau.changePlayer();
                            clearScreen();
                        } else {
                            throw new ImpossibleMove();
                        }

                    } catch (ImpossibleMove e) {
                        System.err.println("Impossible de déplacer le pion ici.");
                    } catch (IndexOutOfBoundsException e) {
                        System.err.println("Coordonnée hors du cadre.");
                    } catch (Exception e) {
                        System.err.println(e);
                        break;
                    }

                } while (!deplace);

            } catch (IncompatinleTeam e) {
                clearScreen();
                System.err.println("Ce n'est pas un de vos pion ou la case est vide.");
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.err.println("Coordonnée hors du cadre.");
            } catch (Exception e) {
                System.err.println(e);
                break;
            }
            // finally
            // {
            // 
            // }
        }
        if(!lose(plateau, players[0])){
            System.out.println(filreadString("VictoireBlancs"));
        }else{
            System.out.println(filreadString("VictoireNoirs"));
        }

    }

    public static boolean isPropositionNumber(String saisie, String[] proposition) {
        try {
            int number = Integer.parseInt(saisie);
            if (0 <= number && number <= proposition.length) {
                return true;
            }
            throw new NumberFormatException();
            
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    public static int displayProposition(String titre, String[] proposition) {
        final String ESPACEMENT = "\n\n"; 
        Scanner sc = new Scanner(System.in);
        String saisie = "";
        while (!isPropositionNumber(saisie, proposition)) {
            System.out.println(alignCenter(titre, 10));
            System.out.print(ESPACEMENT);
            System.out.println("0 Retour");
            for (int i = 1; i < proposition.length + 1; i++) {
                System.out.println(i + " " + proposition[i - 1]);
            }
            System.out.println("\nChoisissez une option en tappant un nombre entre 0 et " + proposition.length);
            System.out.print("\033[0;33m ▶ \033[0m");
            saisie = sc.nextLine();
        }
        
        return Integer.parseInt(saisie);        
    }

    public static void main(String[] args) throws ImpossibleMove, IncompatinleTeam, IOException {
        boolean end = false;
        while(!end) {
            clearScreen();
            System.out.println("\033[0m");
            System.out.println(filreadString("title"));
            int choix = displayProposition(alignCenter("Par des Étudiants en informatique.",103), new String[] {"Jouer", "Credits"});
            if (choix == 1) {
                clearScreen();
                jeu();
            }
            if (choix == 2) {
                clearScreen();
                credits();
            }  
        }
    }
}