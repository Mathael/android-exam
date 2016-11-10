package com.morpion.game;

import java.io.Serializable;

/**
 * Created by fbm on 09/11/16.
 */
public class JeuMorpion9cases implements Serializable {

    // CONSTANTES
    private static int CASE_VIDE = 0;
    private static int JOUEUR1 = 1;
    private static int JOUEUR2 = 2;
    private static int NOMBRE_COLONNES = 3;
    private static int NOMBRE_LIGNES = 3;

    // DATA
    private int[][] grille = {
        {CASE_VIDE,CASE_VIDE,CASE_VIDE},
        {CASE_VIDE,CASE_VIDE,CASE_VIDE},
        {CASE_VIDE,CASE_VIDE,CASE_VIDE}
    };

    public void reset() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = CASE_VIDE;
            }
        }
    }

    public JeuMorpion9cases() {
    }

    /**
     * Positionner le joueur 1 en x (colonne),y (ligne) (3 colonnes, 3 lignes)
     * Retourne vrai si le joueur a bien été affecté à la case
     *
     * @param x index colonne
     * @param y index ligne
     * @return
     */
    public boolean setJoueur1(int x, int y) {
        return setJoueur(JOUEUR1, x, y);
    }

    /**
     * Positionner le joueur 1 en case d'index position (9 cases en tout de 0 à 8)
     * Retourne vrai si le joueur a bien été affecté à la case
     *
     * @param position numéro de la case
     * @return
     */
    public boolean setJoueur1(int position) {
        int[] point = transformePosition(position);
        return setJoueur1(point[0], point[1]);
    }

    /**
     * Positionner le joueur 2 en x (colonne),y (ligne) (3 colonnes, 3 lignes)
     * Retourne vrai si le joueur a bien été affecté à la case
     *
     * @param x index colonne
     * @param y index ligne
     * @return
     */
    public boolean setJoueur2(int x, int y) {
        return setJoueur(JOUEUR2, x, y);
    }

    /**
     * Positionner le joueur 2 en case d'index position (9 cases en tout de 0 à 8)
     * Retourne vrai si le joueur a bien été affecté à la case
     *
     * @param position numéro de la case
     * @return
     */
    public boolean setJoueur2(int position) {
        int[] point = transformePosition(position);
        return setJoueur2(point[0], point[1]);
    }

    /**
     * Retourne vrai si le joueur 1 a gagné
     *
     * @return
     */
    public boolean isJoueur1Win() {

        return isJoueurWin(JOUEUR1);
    }

    /**
     * Retourne vrai si le joueur 2 a gagné
     *
     * @return
     */
    public boolean isJoueur2Win() {

        return isJoueurWin(JOUEUR2);
    }

    /**
     * Retourne vrai si aucun des joueurs n'a gagné
     *
     * @return
     */
    public boolean isEgalite() {
        boolean isEgalite = true;

        //
        int x = 0;
        while (isEgalite && (x < NOMBRE_LIGNES)) {
            int y = 0;
            while (isEgalite && (y < NOMBRE_COLONNES)) {
                isEgalite = existJoueur(x,y);
                y++;
            }
            x++;
        }

        //
        return isEgalite;
    }

    /**
     * Retourne vrai si un joueur est présent sur la case x (colonne), y (ligne)
     *
     * @param x index colonne
     * @param y index ligne
     * @return
     */
    public boolean existJoueur(int x, int y) {
        return grille[x][y] != CASE_VIDE;
    }

    /**
     * Retourne vrai si le joueur 1 est présent sur la case x (colonne), y (ligne)
     *
     * @param x index colonne
     * @param y index ligne
     * @return
     */
    public boolean existJoueur1(int x, int y) {
        return grille[x][y] == JOUEUR1;
    }

    /**
     * Retourne vrai si le joueur 2 est présent sur la case x (colonne), y (ligne)
     *
     * @param x index colonne
     * @param y index ligne
     * @return
     */
    public boolean existJoueur2(int x, int y) {
        return grille[x][y] == JOUEUR2;
    }

    /**
     * Retourne vrai si un joueur est présent à l'index position (0 ... 8)
     *
     * @param position
     * @return
     */
    public boolean existJoueur(int position) {
        int[] point = transformePosition(position);
        return existJoueur(point[0], point[1]);
    }

    /**
     * Retourne vrai si le joueur 1 est présent à l'index position (0 ... 8)
     *
     * @param position
     * @return
     */
    public boolean existJoueur1(int position) {
        int[] point = transformePosition(position);
        return existJoueur1(point[0], point[1]);
    }

    /**
     * Retourne vrai si le joueur 2 est présent à l'index position (0 ... 8)
     *
     * @param position
     * @return
     */
    public boolean existJoueur2(int position) {
        int[] point = transformePosition(position);
        return existJoueur2(point[0], point[1]);
    }

    /**
     * Retourne une chaine de caractère représentant l'état courant de la grille
     * O pour le joueur 1
     * X pour le joueur 2
     *
     * Exemple : {{OXO}{OXX}{XOO}}     ... {{ligne0}{ligne1}{ligne2}}
     *
     * @return
     */
    @Override
    public String toString() {
        String result = "{";

        //
        int y = 0;
        while (y < NOMBRE_LIGNES) {
            int x = 0;
            result = result + "{";
            while (x < NOMBRE_COLONNES) {
                if (existJoueur1(x, y)) {
                    result = result + "O";
                } else if (existJoueur2(x, y)) {
                    result = result + "X";
                } else {
                    result = result + "-";
                }
                x++;
            }
            result = result + "}";
            y++;
        }
        result = result + "}";
        return result;
    }

    //
    private boolean isJoueurWin(int joueur) {

        return  (isJoueur(joueur, 0,0) && isJoueur(joueur, 0,1) && isJoueur(joueur, 0,2))        // Vérification COLONNE
                || (isJoueur(joueur, 1,0) && isJoueur(joueur, 1,1) && isJoueur(joueur, 1,2))
                || (isJoueur(joueur, 2,0) && isJoueur(joueur, 2,1) && isJoueur(joueur, 2,2))

                || (isJoueur(joueur, 0,0) && isJoueur(joueur, 1,0) && isJoueur(joueur, 2,0))     // Vérification LIGNE
                || (isJoueur(joueur, 0,1) && isJoueur(joueur, 1,1) && isJoueur(joueur, 2,1))
                || (isJoueur(joueur, 0,2) && isJoueur(joueur, 1,2) && isJoueur(joueur, 2,2))

                || (isJoueur(joueur, 0,0) && isJoueur(joueur, 1,1) && isJoueur(joueur, 2,2))    // Vérification DIAGONALE
                || (isJoueur(joueur, 0,2) && isJoueur(joueur, 1,1) && isJoueur(joueur, 2,0));
    }

    //
    private boolean setJoueur(int joueur, int x, int y) {
        boolean result = false;

        if (grille[x][y] == CASE_VIDE) {
            grille[x][y] = joueur;
            result = true;
        }

        //
        return result;
    }

    //
    private void setCaseVide(int x, int y) {
        grille[x][y] = CASE_VIDE;
    }

    //
    private boolean isJoueur(int joueur, int x, int y) {
        return grille[x][y] == joueur;
    }

    //
    private int[] transformePosition(int position) {
        int x = 0;
        int y = 0;
        switch (position) {
            case 0:
                x = 0;
                y = 0;
                break;
            case 1:
                x = 1;
                y = 0;
                break;
            case 2:
                x = 2;
                y = 0;
                break;
            case 3:
                x = 0;
                y = 1;
                break;
            case 4:
                x = 1;
                y = 1;
                break;
            case 5:
                x = 2;
                y = 1;
                break;
            case 6:
                x = 0;
                y = 2;
                break;
            case 7:
                x = 1;
                y = 2;
                break;
            case 8:
                x = 2;
                y = 2;
                break;
        }

        int[] result = {x,y};
        return result;

    }

}
