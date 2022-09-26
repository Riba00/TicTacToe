import java.util.Scanner;

public class tresEnRalla {
    static String[][] tablero = new String[3][3];

    public static void main(String[] args) {
        crearTablero();
        jugar();
    }

    public static void crearTablero() {
        /*
        CREA UNA ARRAY MULTIDIMENSIONAL AMB TOT "-"
         */
        //TABLERO
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = "-";
            }
        }
    }

    public static void mostrarTablero() {
        /*
        MOSTRA L'ARRAY MULTIDIMENSIONAL AMB ELS VALORS
         */
        System.out.println("  1  2  3");
        char eixY = 'A';
        for (int i = 0; i < tablero.length; i++) {
            System.out.print(eixY + " ");
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + "  ");
            }
            System.out.println();
            eixY++;
        }
    }

    public static void jugar() {

        Scanner teclat = new Scanner(System.in);
        //VARIABLE PER A SABER EL TORN
        int saberTurno = 0;
        //VARIABLE PER A SABER QUI HA TIRAT
        int turnoJugador = 0;
        //VARIABLES PER A MODIFICAR EL TABLERO
        int numeroFila = 0;
        int numeroColumna = 0;

        String coordenada;
        String respostaSeguirJugant;

        boolean partidaAcabada;
        boolean seguirJugant;

        //INICI BUCLE TORNAR A JUGAR
        do {
            //INICI BUCLE PARTIDA
            do {
                partidaAcabada = false;
                seguirJugant = false;
                mostrarTablero();
                //DECIDIR QUI LI TOCA TIRAR
                if (saberTurno % 2 == 0) {
                    System.out.print("Jugador 1: ");
                    coordenada = teclat.nextLine();
                    turnoJugador = 1;
                } else {
                    System.out.print("Jugador 2: ");
                    coordenada = teclat.nextLine();
                    turnoJugador = 2;
                }
                //PROBEM SI LA COORDENADA ES CORRECTA I LLANCEM EXCEPCIONS
                try {
                    numeroFila = (coordenada.toUpperCase().charAt(0) - 65);
                    numeroColumna = (Integer.parseInt(String.valueOf(coordenada.charAt(1))) - 1);
                    //BUIDA EXCEPTION
                    if (tablero[numeroFila][numeroColumna] == "-") {
                        throw new buidaException();
                    }
                    //OCUPADA EXCEPTION
                    if (tablero[numeroFila][numeroColumna] == "X" || tablero[numeroFila][numeroColumna] == "O") {
                        throw new ocupadaException();
                    }

                } catch (buidaException e) {
                    System.out.println("La casella és buida");
                    System.out.println();
                    pintarCasella(turnoJugador, numeroFila, numeroColumna);
                    saberTurno++;

                } catch (ocupadaException e) {
                    System.out.println("La casella està ocupada");
                    System.out.println();
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Coordenada incorrecta");
                    System.out.println();
                }

                //DECIDIR QUI HA GUANYAT
                switch (verificarGuanyador()) {
                    case 1:
                        System.out.println("Guanya Jugador 1");
                        partidaAcabada = true;
                        break;
                    case 2:
                        System.out.println("Guanya Jugador 2");
                        partidaAcabada = true;
                        break;
                    case 3:
                        System.out.println("Empat");
                        partidaAcabada = true;
                        break;
                }
            } while (!partidaAcabada);
            do {
                System.out.println("Vols seguir jugant? (S) Si   (N) No");
                System.out.print("Resposta: ");
                respostaSeguirJugant = teclat.nextLine();
            } while (!respostaSeguirJugantCorrecta(respostaSeguirJugant));
            if (respostaSeguirJugant.charAt(0) == 'S' || respostaSeguirJugant.charAt(0) == 's') {
                seguirJugant = true;
                crearTablero();
            }
        } while (seguirJugant);
        System.out.println("Adeu, fins la proxima");


    }

    public static void pintarCasella(int jugador, int fila, int columna) {
        /*
        EN FUNCIÓ DEL JUGADOR POSA UNA LLETRA O UNA ALTRA
         */

        if (jugador == 1) {
            tablero[fila][columna] = "X";
        }
        if (jugador == 2) {
            tablero[fila][columna] = "O";
        }
    }

    public static int verificarVertical() {
        /*
        Verifica si a tablero hi ha un 3 en ratlla vertical
        Retorna:
            0 -> Si no hi ha res
            1 -> Si el contador1 arriba a 3 (jugador1 guanya)
            2 -> Si el contador2 guanya a 3 (jugador2 guanya)
         */
        if (tablero[0][0] == "X" && tablero[1][0] == "X" && tablero[2][0] == "X") return 1;
        if (tablero[0][0] == "O" && tablero[1][0] == "O" && tablero[2][0] == "O") return 2;
        if (tablero[0][1] == "X" && tablero[1][1] == "X" && tablero[2][1] == "X") return 1;
        if (tablero[0][1] == "O" && tablero[1][1] == "O" && tablero[2][1] == "O") return 2;
        if (tablero[0][2] == "X" && tablero[1][2] == "X" && tablero[2][2] == "X") return 1;
        if (tablero[0][2] == "O" && tablero[1][2] == "O" && tablero[2][2] == "O") return 2;
        return 0;
    }

    public static int verificarHoritzontal() {
        /*
        Verifica si a tablero hi ha un 3 en ratlla vertical
        Retorna:
            0 -> Si no hi ha res
            1 -> Si el jugador 1 ha fet 3 en ratlla (jugador1 guanya)
            2 -> Si el jugador 2 ha fet 3 en ratlla (jugador2 guanya)
         */
        if (tablero[0][0] == "X" && tablero[0][1] == "X" && tablero[0][2] == "X") return 1;
        if (tablero[0][0] == "O" && tablero[0][1] == "O" && tablero[0][2] == "O") return 2;
        if (tablero[1][0] == "X" && tablero[1][1] == "X" && tablero[1][2] == "X") return 1;
        if (tablero[1][0] == "O" && tablero[1][1] == "O" && tablero[1][2] == "O") return 2;
        if (tablero[2][0] == "X" && tablero[2][1] == "X" && tablero[2][2] == "X") return 1;
        if (tablero[2][0] == "O" && tablero[2][1] == "O" && tablero[2][2] == "O") return 2;
        return 0;
    }

    public static int verificarDiagonal() {
        /*
        Verifica si a tablero hi ha un 3 en ratlla en diagonal
        Retorna:
            0 -> Si no hi ha res
            1 -> Si el jugador 1 ha fet 3 en ratlla (jugador1 guanya)
            2 -> Si el jugador 2 ha fet 3 en ratlla (jugador2 guanya)

         */
        //DIAGONAL DESCENDENT
        if (tablero[0][0] == "X" && tablero[1][1] == "X" && tablero[2][2] == "X") return 1;
        if (tablero[0][0] == "O" && tablero[0][1] == "O" && tablero[0][2] == "O") return 2;

        //DIAGONAL ASCENDENT
        if (tablero[2][0] == "X" && tablero[1][1] == "X" && tablero[0][2] == "X") return 1;
        if (tablero[2][0] == "O" && tablero[1][1] == "O" && tablero[0][2] == "O") return 2;

        return 0;
    }

    public static int verificarGuanyador() {
        /*
        VERIFICA SI ALGUN JUGADOR HA FET UN 3 EN RATLLA
            1 -> GUANYA JUGADOR 1
            2 -> GUANYA JUGADOR 2
         */
        try {
            if (verificarHoritzontal() == 1 || verificarVertical() == 1 || verificarDiagonal() == 1)
                throw new guanyaJugador1Exception();
            if (verificarHoritzontal() == 2 || verificarVertical() == 2 || verificarDiagonal() == 2)
                throw new guanyaJugador2Exception();
            if (verificarEmpat() == 1) throw new empatException();

        } catch (guanyaJugador1Exception e) {
            return 1;
        } catch (guanyaJugador2Exception e) {
            return 2;
        } catch (empatException e) {
            return 3;
        }
        return 0;
    }

    public static int verificarEmpat() {
        /*
        VERIFICA SI LA PARTIDA HA ACABAT AMB EMPAT
            0 -> NO HI HA EMPAT
            1 -> EMPAT
         */
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == "-") {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static boolean respostaSeguirJugantCorrecta(String resposta) {
        /*
        CONTROLAR LA RESPOSTA DE REINICIAR LA PARTIDA
         */
        char primeraLletra = resposta.toUpperCase().charAt(0);
        if (primeraLletra == 'S' || primeraLletra == 'N') return true;
        return false;
    }
}
