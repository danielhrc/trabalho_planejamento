package simulation;

import desmoj.core.simulator.EventOf2Entities;
import desmoj.core.simulator.Model;

public class EventoTerminoLavagem extends EventOf2Entities<MaquinaLavar, Cliente> {

	/**
	 * Método construtor do EventoTerminoLavagem.
	 *
	 * Cria um novo evento responsável por terminar
	 * a lavagem das roupas de um cliente da lavanderia.
	 * 
	 * A criação desse evento ocorre por meio da chamada
	 * do método construtor da super-classe, ou seja, da classe EventOf2Entities.
	 * 
	 * parâmetro owner: indica o modelo ao qual o evento está associado.
	 *                  
	 * parâmetro name: indica o nome do evento.
	 * 
	 * parâmetro showInTrace: é um flag que indica se o evento deve ou não
	 *                        produzir saídas para um trace de saída da simulação.
	 */
	public EventoTerminoLavagem(Model owner, String name, boolean showInTrace) {
		super (owner, name, showInTrace);
	}

	@Override
	/**
	 * Esse método é invocado sempre que o relógio da simulação atinge o momento
	 * para o qual o evento relacionado ao término da lavagem das roupas de um cliente
	 * da lavanderia foi escalonado.
	 * Ele indica o que deve ser feito quando esse momento é alcançado.
	 * 
	 * Nesse caso, a máquina de lavar utilizada pelo cliente (passada como
	 * parâmetro para esse método) deve ser liberada.
	 */
	public void eventRoutine(MaquinaLavar maquinaLavar, Cliente cliente) {
		// Identificação do modelo ao qual o evento relacionado ao término da lavagem de roupas do cliente pertence.
		Lavanderia modeloLavanderia = (Lavanderia) getModel();
		
		modeloLavanderia.sendTraceNote(maquinaLavar + " terminou a lavagem de roupas.");
		
		// A máquina de lavar que foi utilizada pelo cliente para lavar suas roupas deve ser liberada.
		modeloLavanderia.liberarMaquinaLavar(maquinaLavar);		
		
	}
}