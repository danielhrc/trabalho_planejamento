package simulation;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.TimeSpan;

public class MaquinaLavar extends Entity{
	
	/**
	 * ocupada: variável booleana que indica se a máquina de lavar está ocupada,
	 * sendo utilizada por algum cliente da lavanderia;
	 * ou livre para servir novos clientes	que chegarem à lavanderia.
	 */
	private boolean ocupada;
	
	/**
	 * Variável que indica o cliente que está utilizando a máquina de lavar em um determinado momento,
	 * caso a máquina de lavar esteja ocupada.
	 */
	private Cliente cliente;
	
   /**
   * Método construtor da MaquinaLavar.
   *
   * Cria uma nova máquina de lavar, responsável por lavar as roupas de clientes
   * que chegam à lavanderia.
   * Adicionalmente, o atributo "ocupada" é inicializado. 
   * 
   * A criação da máquina de lavar ocorre por meio da chamada
   * do método construtor da super-classe, ou seja, da classe Entity.
   * 
   * parâmetro owner: indica o modelo ao qual a máquina de lavar está associada.
   *                  
   * parâmetro name: indica o nome da máquina de lavar.
   * 
   * parâmetro showInTrace: é um flag que indica se a máquina de lavar deve ou não
   *                        produzir saídas para um trace de saída da simulação.
   */      
   public MaquinaLavar(Model owner, String name, boolean showInTrace) {
	
	   super (owner, name, showInTrace);
		
	   this.ocupada = false;
	}

   // Método responsável por setar o atributo que indica se a máquina de lavar está ou não ocupada.
   public void setOcupada(boolean estaOcupada) {
	   this.ocupada = estaOcupada;
   }

   /** 
    * Método responsável por recuperar o tempo durante o qual a máquina de lavar será utilizada por
    * um cliente (passado como parâmetro) e escalonar o evento responsável pela liberação dessa máquina de lavar. 
    */
   public void lavar(Cliente cliente) {
	   
	   Lavanderia modeloLavanderia;
	   double tempoLavagem;
	   EventoTerminoLavagem eventoTerminoLavagem;
	   
	   // Armazena o cliente que está utilizando a máquina de lavar nesse momento.
	   this.cliente = cliente;
	   
	   // Identificação do modelo ao qual a máquina de lavar está associada.
	   modeloLavanderia = (Lavanderia) getModel();
	   
	   /**
	    * O tempo durante o qual a máquina de lavar deve permanecer servindo esse cliente é determinado, 
		* de acordo com a distribuição de probabilidade do tempo de serviço da máquina de lavar.
		*/
	   tempoLavagem = modeloLavanderia.getTempoLavagem();
	   modeloLavanderia.sendTraceNote(this + " serve " + cliente + " por " + tempoLavagem + " minutos.");
	   
	   // O evento correspondente ao término da lavagem das roupas desse cliente da lavanderia, por essa máquina de lavar, é criado...
	   eventoTerminoLavagem = new EventoTerminoLavagem(modeloLavanderia, "Evento relacionado ao término da lavagem das roupas do cliente", true);
	   // e escalonado.
	   eventoTerminoLavagem.schedule(this, cliente, new TimeSpan (tempoLavagem));
   }

   // Método responsável por retornar se a máquina de lavar está ou não ocupada.
   public boolean getOcupada() {
	   return (this.ocupada);
   }
   
   // Método responsável por retornar o cliente que está utilizando a máquina de lavar.
   public Cliente getCliente() {
	   return (this.cliente);
   }
}