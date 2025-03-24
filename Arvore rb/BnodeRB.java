
public class BnodeRB {
    int x;
    BnodeRB esq, dir, pai;
    char cor; // V - vermelho, P - Preto

    public BnodeRB(int valor){
        x = valor;
        esq = dir = pai = null;
        cor = 'P';
    }
    public boolean VP(){
        if(cor == 'P'){
            return true;
        }
        else{
            return false;
        }
    }
    public int AlturaNegra(BnodeRB no){
        if(no == null){
            return 1;
        }
        else{
            int esqAltura = AlturaNegra(no.esq);
            if(esqAltura==0){return esqAltura;}
            int dirAltura = AlturaNegra(no.dir);
            if(dirAltura==0){return dirAltura;}
            if(esqAltura!=dirAltura){
                return 0;
            }
            else{
                return esqAltura + (no.VP() ? 1 : 0);
            }
           
        }
    }
    // public BnodeRB remover(BnodeRB no, int i){
    //     if(i < no.getX()){
    //         no.esq = remover(no.esq, i);
    //     }
    //     else if( i > no.getX()){
    //         no.dir = remover(no.dir, i);
    //     }
    //     else{
    //         // problema
    //         // caso sem filhos
    //         if(no.esq == null && no.dir == null){
    //             return null;
    //         }
    //         // caso com 1 nó filhos
    //         else if(no.esq == null) return no.dir;
    //         else if(no.dir == null) return no.esq;

    //         //caso com 2 nós filhos
    //         else{
    //             BnodeRB aux = emin(no.dir);
    //             no.x = aux.x;
    //             no.dir = remover(no.dir, aux.x);
    //         }
    //     }
    //     return no;
    // }

    // public BnodeRB emin(BnodeRB no){
    //         BnodeRB atual = no;
    //         while(atual.esq!=null){
    //             atual = atual.esq;
    //         }
    //         return atual;
    //     }

    public void show(){

        System.out.println(cor + " | " + x + " | ");      // RAIZ
        if(esq!=null) esq.show();   //ESQ
        if(dir!=null) dir.show();    // DIR
    }
    public void showLeaf(){
        if(dir==null&&esq==null){
            System.out.println(x); 
        }
        else{
            if(dir!=null) dir.showLeaf();    // DIR      // RAIZ
            if(esq!=null) esq.showLeaf();   // ESQ
        }
    }
    
    public int size(){
        int e = 0, d = 0;
        if(esq!=null){ e = esq.size();}
        if(dir!=null){ d = dir.size();}
        return 1 + e + d;
    }

    public int Altura(){
        int e = 0, d = 0;
        if(dir==null&esq==null){
            return 1;
        }
        else{
            if(esq!=null){
                e = 1 + esq.Altura();
            }
            if(dir!=null){
                d = 1 + dir.Altura();
            }

            if(e>d){
                return e;
            }
            else{
                return d;
            }
        }
    }

    public int Leafs(){
        int e = 0, d = 0;
        if(esq!=null) e = esq.Leafs();
        if(dir!=null) d = dir.Leafs();
        if(esq==null && dir==null){
            return 1 + e + d;
        }
        else{
            return e + d;
        }
    }
    public int countIntern(){
        int e = 0, d = 0;
        if(esq!=null) e = esq.countIntern();
        if(dir!=null) d = dir.countIntern();
        if(esq==null && dir==null){
            return e + d;
        }
        else{
            return 1+e + d;
        }
    }
    public int sumLeafs(){
        int e = 0, d = 0;
        if(esq!=null) e = esq.sumLeafs();
        if(dir!=null) d = dir.sumLeafs();
        if(esq==null && dir==null){
            return x + e + d;
        }
        else{
            return e + d;
        }
    }
    public int sumIntern(){
        int e = 0, d = 0;
        if(esq!=null) e = esq.sumIntern();
        if(dir!=null) d = dir.sumIntern();
        if(esq==null && dir==null){
            return e + d;
        }
        else{
            return x+e + d;
        }
    }
    public int soma(){
        int e = 0, d = 0;
        if(esq!=null) e = esq.soma();
        if(dir!=null) d = dir.soma();
        return x + e + d;
    }
    public int Maior(int big){
        if(x>big){
            big = x;
        }
        if(dir!=null) big = dir.Maior(big); 
        if(esq!=null) big = esq.Maior(big);
        return big;

    }
    
    public void AltSlct(int N,int lvl){
        if(esq!=null){ 
            esq.AltSlct(N,lvl+1);
        }
        if(dir!=null){ 
            dir.AltSlct(N,lvl+1);
        } 
        if(lvl==N){
           System.out.println(x);
        }
    }
    public void SrchValue(int N,int lvl){
        if(esq!=null){ 
            esq.AltSlct(N,lvl+1);
        }
        if(dir!=null){ 
            dir.AltSlct(N,lvl+1);
        } 
        if(x==N){
           System.out.println(x);
        }
    }
}