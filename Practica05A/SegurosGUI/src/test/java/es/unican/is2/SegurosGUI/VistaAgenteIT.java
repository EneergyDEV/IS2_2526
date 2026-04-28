package es.unican.is2.SegurosGUI;

import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import es.unican.is2.SegurosBusiness.GestionSeguros;
import es.unican.is2.SegurosDAOH2.ClientesDAO;
import es.unican.is2.SegurosDAOH2.SegurosDAO;

public class VistaAgenteIT {

    private FrameFixture demo;
    private GestionSeguros negocio;
    private ClientesDAO clientesDao;
    private SegurosDAO segurosDao; 
    @BeforeEach
    public void setUp() {
     
        clientesDao = new ClientesDAO();
        segurosDao = new SegurosDAO();

        negocio = new GestionSeguros(clientesDao, segurosDao); 

        VistaAgente gui = new VistaAgente(negocio, negocio, negocio);
        
        
        
        demo = new FrameFixture(gui);
        gui.setVisible(true);
    }

    @AfterEach
    public void tearDown() {
        demo.cleanUp();
    }

    @Test
    public void testConsultaClienteValido() {

        demo.textBox("txtDNICliente").enterText("11111111A");
       
        
        
        
        demo.button("btnBuscar").click();

        demo.textBox("txtNombreCliente").requireText("Juan");
        
        

        demo.list().requireItemCount(3);
    }
    
    @Test
    public void testConsultaClienteSinSeguros() {
       
        demo.textBox("txtDNICliente").enterText("33333333A");
        demo.button("btnBuscar").click();
        
        demo.textBox("txtNombreCliente").requireText("Luis");
        demo.list().requireItemCount(0);
    }

    @Test
    public void testConsultaClienteNoExiste() {
     
        demo.textBox("txtDNICliente").enterText("12345678Z");
        demo.button("btnBuscar").click();
    
        demo.textBox("txtNombreCliente").requireText("Error en BBDD");
        demo.list().requireItemCount(0);
    }
}