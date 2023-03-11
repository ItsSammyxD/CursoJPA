/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curso.jpa.controlador;

import curso.jpa.controlador.exceptions.NonexistentEntityException;
import curso.jpa.entidad.TbPersona;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author administrador
 */
public class TbPersonaJpaController implements Serializable {

    public TbPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Empleados-PU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TbPersonaJpaController() {
        
    }
    
    

    public void create(TbPersona tbPersona) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tbPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TbPersona tbPersona) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tbPersona = em.merge(tbPersona);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbPersona.getIdPersona();
                if (findTbPersona(id) == null) {
                    throw new NonexistentEntityException("The tbPersona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TbPersona tbPersona;
            try {
                tbPersona = em.getReference(TbPersona.class, id);
                tbPersona.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbPersona with id " + id + " no longer exists.", enfe);
            }
            em.remove(tbPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TbPersona> findTbPersonaEntities() {
        return findTbPersonaEntities(true, -1, -1);
    }

    public List<TbPersona> findTbPersonaEntities(int maxResults, int firstResult) {
        return findTbPersonaEntities(false, maxResults, firstResult);
    }

    private List<TbPersona> findTbPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TbPersona.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TbPersona findTbPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TbPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TbPersona> rt = cq.from(TbPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
