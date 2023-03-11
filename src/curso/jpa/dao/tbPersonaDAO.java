package curso.jpa.dao;

import curso.jpa.controlador.TbPersonaJpaController;
import curso.jpa.entidad.TbPersona;

/**
 *
 * @author administrador
 */
public class tbPersonaDAO {

    private TbPersonaJpaController tjc = new TbPersonaJpaController();
    private TbPersona persona = new TbPersona();

    public String insertarPersona(String nombres, String apellidos, int edad, String telefono) {
        try {
            persona.setIdPersona(Integer.BYTES);
            persona.setNombres(nombres);
            persona.setApellidos(apellidos);
            persona.setEdad(edad);
            persona.setTelefono(telefono);
            tjc.create(persona);
        } catch (Exception e) {
        }

        return null;
    }

    public String actualizarPersona() {
        return null;
    }

    public String eliminarPersona() {
        return null;
    }

}
