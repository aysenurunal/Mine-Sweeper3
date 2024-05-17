import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;


public class Main {
    public static void main(String[] args) {
        System.out.println("Kaça kaçlık matrixte oynamak istiyorsunuz:  ");
        Scanner sc= new Scanner(System.in);
        int a= sc.nextInt();
        int b=sc.nextInt();
        String [][] board = new String[a][b];
        int mine_num= (a*b)/4;
        String [][] game_board = new String[a][b];
        /*Buraya kadar kaça kaçlık boardda oynamak istiyor onu aldım, mayın sayısını buldum.
        Buradan sonraki for döngüsünde boardu mayınsız atıyorum (hepsi - gibi).
        bi de game_board olarak tanımlanan oyuncuya oyun sırasında göstereceğim board
         */
        for (int i = 0; i < a; i++) {
            for (int j=0;j<b;j++){
                board [i][j]="-";
                game_board [i][j]="-";
            }
        }

        //while döngüsünde randomla mayınları board a atıyorum. Ve oyunun basında board u yazdırıyorum.
        Random rand = new Random();
        int count =0;
        while(count<mine_num){
            int r1 = rand.nextInt(a);
            int r2 = rand.nextInt(b);
            if (board[r1][r2]=="*"){
                continue;
            }
            board[r1][r2]="*";
            count +=1;
        }
        //burada mine_number_board olarak tanımladığım etrafında kaç mayın var her yer için ona bakıyorum.
        // 9 olarak yazdırdığım mayınların yeri çünkü bi noktanın etrafında max 8 mayın olabilir.
        // oyuncuya o giriş yaptıkça bu tahtadan açıcam değerleri
        int [][] mine_number_board=new int [a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (board[i][j].equals("*")) {
                    mine_number_board[i][j] = 9;
                } else {
                    int count2 = 0;
                    for (int x = Math.max(0, i - 1); x <= Math.min(a - 1, i + 1); x++) {
                        for (int y = Math.max(0, j - 1); y <= Math.min(b - 1, j + 1); y++) {
                            if (board[x][y].equals("*")) {
                                count2++;
                            }
                        }
                    }
                    mine_number_board[i][j] = count2;
                }
            }
        }
        for (int i = 0; i < a; i++) {
            for (int j=0;j<b;j++){
                System.out.print(mine_number_board[i][j] +" ");
            }
            System.out.println();
        }

        System.out.println("Mayınların Konumu: ");
        for (int i = 0; i < a; i++) {
            for (int j=0;j<b;j++){
                System.out.print(board[i][j] +" ");
            }
            System.out.println();
        }
        System.out.println("===========================");
        System.out.println("Mayın Tarlası Oyuna Hoşgeldiniz !");
        for (int i = 0; i < a; i++) {
            for (int j=0;j<b;j++){
                System.out.print(game_board[i][j] +" ");
            }
            System.out.println();
        }
        /*buradan itibaren oyunu baslatıyorum.
        maximum (a*b - mayın sayısı) kere oyunu oynayabileceğinden game_counterı tanımladım, while sonsuza kadar dönmesin diye.
        daha önce girilen satır&sütün ikilisi bi daha girilmesin diye inputs diye bi list tanımladım.
        eğer daha önce girilmişse counterı arttırmıyorum.*/
        int game_counter=0;
        int [] inputs= new int [a*b];
        while(game_counter<(a*b-mine_num)) {
            System.out.println("Satır Giriniz: ");
            int row = sc.nextInt();
            System.out.println("Sütun Giriniz: ");
            int column = sc.nextInt();
            //buradaki ifler de doğru aralıkta row column değerleri girilsin diye
            if (row < 0 || row > a) {
                System.out.println("please enter proper row number");
                continue;
            }
            if (column < 0 || column > b) {
                System.out.println("please enter proper column number");
                continue;
            }
            //double input=(row,column);
            //inputs[game_counter]=input;

            System.out.println("===========================");
            if (board[row][column] == "*") {
                System.out.println("Game Over! ");
                System.out.println("===========================");
                break;
            }
            if (game_counter == (a * b - mine_num) - 1) {
                System.out.println("You won the game! Congrats!!! ");
                break;
            }
            game_counter += 1;
            int taken_from_gamer=mine_number_board[row][column];
            String s=String.valueOf(taken_from_gamer);

            game_board[row][column] = s;
            for (int i = 0; i < a; i++) {
                for (int j = 0; j < b; j++) {
                    System.out.print(game_board[i][j] + " ");
                }
                System.out.println();
            }
        }


        }


        }

