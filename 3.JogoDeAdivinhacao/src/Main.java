void main() {

    Scanner sc = new Scanner(System.in);
    Random gerador = new Random();
    int totalTentativas = 1;

    int numeroSorteado = gerador.nextInt(100)+1;


    while (true) {
        System.out.print("Numero: ");
        int numeroEscolhido = sc.nextInt();

        int distancia = Math.abs(numeroSorteado - numeroEscolhido);
        if(numeroEscolhido == numeroSorteado){
            System.out.println("Numero sorteado: " + numeroSorteado);
            System.out.println("Acertou");
            System.out.println("Total de tentavivas: " + totalTentativas);
            break;
        }
        else if(numeroEscolhido < numeroSorteado){
            totalTentativas++;
            System.out.println("Muito baixo!");
            System.out.println("Você está a "+ distancia + " números de distância");
        }else {
            totalTentativas++;
            System.out.println("Muito alto!");
            System.out.println("Você está a "+ distancia + " números de distância");
        }
    }
}
