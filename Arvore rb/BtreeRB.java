
public class BtreeRB {
    private BnodeRB raiz;

    public BtreeRB(){
        raiz = null;
    }

    public void add(int valor){
        BnodeRB no = raiz;
        BnodeRB pai = null;
        //Percorrer árvore, para inserir nó no local correto e definir seu pai
        while(no!= null){
            pai = no;
            if(valor < no.x){
                no = no.esq;
            }
            else{
                no = no.dir;
            }
        }

        //Inserção
        BnodeRB novo_no = new BnodeRB(valor);
        novo_no.cor = 'V';
        if(pai == null){
            raiz = novo_no;
        }
        else if( valor < pai.x){
            pai.esq = novo_no;
        }
        else{
            pai.dir = novo_no;
        }
        novo_no.pai = pai;
        RecolorirBalanceamento(novo_no);
    }

    private void rotacaoesq(BnodeRB no) {
        BnodeRB pai = no.pai;
        BnodeRB filhodir = no.dir;

        no.dir = filhodir.esq;
        if (filhodir.esq != null) {
            filhodir.esq.pai = no;
        }
        // Rotaciona para esquerda
        filhodir.esq = no;
        no.pai = filhodir;
        // Reorganiza quem é pai de quem
        ReorganizaPaiFilho(pai, no, filhodir);
    }

    private void rotacaosdir(BnodeRB no){
        BnodeRB pai = no.pai;
        BnodeRB filhoesq = no.esq;
        no.esq = filhoesq.dir;
        if(filhoesq.dir!= null){
            filhoesq.dir.pai = no;
        }
        //Rotaciona para a direita
        filhoesq.dir = no;
        no.pai = filhoesq;
        // Reorganiza quem é pai de quem
        ReorganizaPaiFilho(pai,no,filhoesq);
    }
    private void ReorganizaPaiFilho(BnodeRB pai, BnodeRB antgirmao, BnodeRB nvirmao){
        if(pai == null){
            raiz = nvirmao;
        }
        else if( pai.esq ==antgirmao ){
            pai.esq = nvirmao;
        }
        else if(pai.dir == antgirmao){
            pai.dir = nvirmao;
        }
        else {
            System.out.println("Nó sem pai");
        }
        if(nvirmao != null){
            nvirmao.pai = pai;
        }
    }

    public BnodeRB SrchValue(int x) {
        BnodeRB aux = raiz;
        while (aux != null) {
            if (x == aux.x) {
                return aux;
            } else if (x < aux.x) {
                aux = aux.esq;
            } else {
                aux = aux.dir;
            }
        }

        return null;
    }
    private void RecolorirBalanceamento(BnodeRB no) {
        //Variável pai do nó que acaboiu de ser inserido
        BnodeRB pai = no.pai;

        // Caso 1: Se pai é nulo está na raiz, então fim de processo de balanceamento.
        if (pai == null) {
            //Raiz TEM que ser Preta
             no.cor = 'P';
            return;
        }

        // Se o pai é preto, então fim de processo de balancemento. Está correto.
        if (pai.cor == 'P') {
            return;
        }
        // Funções para caso Pai vermelho
        // Variável vo do nó inserido
        BnodeRB vo = pai.pai;

        // Caso 2:
        //  Avó não existe, então o pai é a RAIZNot having a grandparent means that parent is the root.
        if (vo == null) {
            // Método chamado apenas para os nós Vermelhos, então deve-se recolorir a RAIZ para preto obrigatóriamente para que não haja erro.
            pai.cor = 'P';
            return;
        }

        // Chamada da função para pegar o Tio do nó para verificaçãao de balanceamento.
        BnodeRB tio = getTio(pai);

        // Caso 3: Tio vermelho, então apenas recolorir pai, vo e tio.
        if (tio != null && tio.cor == 'V') {
            pai.cor = 'P';
            vo.cor = 'V';
            tio.cor = 'P';

            // Chamada recursiva da função para o vo para caso ele seja RAIZ e haja recoloração ou ele tem um pai que é vermelho e precisa de balanceamento.
            RecolorirBalanceamento(vo);
        }

        // Pai é filho Esquerdo do Vo
        else if (pai == vo.esq) {
            // Caso 4: Tio é preto e o nó forma um caminho Zigue-zague com seu vô
            if (no == pai.dir) {
                //Faz uma rotação esquerda para ficarem alinhados
                rotacaoesq(pai);

                // O nó será a nova raiz da sub-árvore rotacionada.
                pai = no;
            }

            // Caso 5: Tio é preto e o nó está alinhado com o seu Vô.
            rotacaosdir(vo);

            // Recoloração do pai original e o Vô.
            pai.cor = 'P';
            vo.cor = 'V';
        }

        // Pai é filho Direito do Vo
        else {
            // Caso 4: Tio é preto e o nó forma um caminho Zigue-zague com seu vô
            if (no == pai.esq) {
                 //Faz uma rotação direita para ficarem alinhados
                rotacaosdir(pai);

                // O nó será a nova raiz da sub-árvore rotacionada.
                pai = no;
            }

            // Caso 5: Tio é preto e o nó está alinhado com o seu Vô.
            rotacaoesq(vo);

            // Recoloração do pai original e o Vô.
            pai.cor = 'P';
            vo.cor = 'V';
        }
    }
    //Função para retornar o tio do nó
    private BnodeRB getTio(BnodeRB pai) {
        BnodeRB vo = pai.pai;
        if (vo.esq == pai) {
            return vo.dir;
        } else{
            return vo.esq;
        }
    }
    public void AlturaNegra(){
        if(raiz!=null){
            System.out.println("Altura Negra: "+ raiz.AlturaNegra(raiz));
        }
        else{
            System.out.println("Altura Negra: 0 Arvore Vazia");
        }
    }

    public void show(){
        if(raiz!=null) raiz.show();
    }

    public void showLeaf(){
        if(raiz!=null) raiz.showLeaf();
    }

    public int size(){
        if(raiz!=null){
            return raiz.size();
        }
        else{
            return 0;
        }
    }

    public int Leafs(){
        if(raiz!=null){
            return raiz.Leafs();
        }
        else{
            return 0;
        }
    }

    public int countIntern(){
        if(raiz!=null){
            return raiz.countIntern();
        }
        else{
            return 0;
        }
    }

    public int sumLeafs(){
        if(raiz!=null){
            return raiz.sumLeafs();
        }
        else{
            return 0;
        }
    }

    public int sumIntern(){
        if(raiz!=null){
            return raiz.sumIntern();
        }
        else{
            return 0;
        }
    }

    public int Maior(){
        if(raiz!=null){ 
            return raiz.Maior(raiz.x);
        }
        else{ 
            return 0;
        }
    }

    public int soma(){
        if(raiz!=null){
            return raiz.soma();
        }
        else{
            return 0;
        }
    }
    
    public void AltSlct(int N){
        if(N>=0){
            raiz.AltSlct(N,0);
        }
        else{
            System.out.println("Nível Inválido");
        }
    }

}