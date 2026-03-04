package es.unican.is2.SegurosBusiness;

import es.unican.is2.SegurosCommon.*;
public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros{

	private IClientesDAO clientesDAO;
    private ISegurosDAO segurosDAO;
	
	
	
	public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
		this.clientesDAO = clientesDAO;
		this.segurosDAO = segurosDAO;
	}

	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		return clientesDAO.cliente(dni);
	}

	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		return segurosDAO.seguroPorMatricula(matricula);
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		Cliente cli = clientesDAO.cliente(dni);
		if (cli == null) {
			throw new OperacionNoValida("El cliente no existe");
		}
		if (segurosDAO.seguroPorMatricula(s.getMatricula()) != null) {
			throw new OperacionNoValida("El vehículo ya tiene seguro");
		}
		cli.getSeguros().add(s);
		segurosDAO.creaSeguro(s);
		clientesDAO.actualizaCliente(cli);
		return s;
	}

	@Override
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
		Cliente cli = clientesDAO.cliente(dni);
		if (cli == null) {
			throw new OperacionNoValida("El cliente no existe");
		}
		Seguro seg = segurosDAO.seguroPorMatricula(matricula);
		if (seg == null) {
			throw new OperacionNoValida("El vehiculo no tiene seguro");
		}
		if (!cli.getSeguros().contains(seg)) {
			throw new OperacionNoValida("El seguro no es del cliente");
		}
		cli.getSeguros().remove(seg);
		segurosDAO.eliminaSeguro(seg.getId());
		clientesDAO.actualizaCliente(cli);
		return seg;
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		Seguro seg = segurosDAO.seguroPorMatricula(matricula);
		if (seg == null) {
			return null;
		}
		seg.setConductorAdicional(conductor);
		segurosDAO.actualizaSeguro(seg);
		return seg;
	}

	@Override
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		Cliente cli = clientesDAO.cliente(c.getDni());
		if (cli != null) {
			return null;
		}
		clientesDAO.creaCliente(cli);
		return cli;
	}

	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
		Cliente cli = clientesDAO.cliente(dni);
		if (cli == null) {
			throw new OperacionNoValida("El cliente no existe");
		}
		if (!cli.getSeguros().isEmpty()) {
			throw new OperacionNoValida("El cliente tiene seguros a su nombre");
		}
		return clientesDAO.eliminaCliente(dni);
	}

	
	
	
	
}
