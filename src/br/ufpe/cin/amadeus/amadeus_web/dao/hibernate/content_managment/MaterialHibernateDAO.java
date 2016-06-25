/**
Copyright 2008, 2009 UFPE - Universidade Federal de Pernambuco
 
Este arquivo � parte do programa Amadeus Sistema de Gest�o de Aprendizagem, ou simplesmente Amadeus LMS
 
O Amadeus LMS � um software livre; voc� pode redistribui-lo e/ou modifica-lo dentro dos termos da Licen�a P�blica Geral GNU como
publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 
Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 
Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre (FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA.
**/

package br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.content_managment;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufpe.cin.amadeus.amadeus_web.dao.content_managment.MaterialDAO;
import br.ufpe.cin.amadeus.amadeus_web.dao.hibernate.GenericHibernateDAO;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.Material;
import br.ufpe.cin.amadeus.amadeus_web.domain.content_management.MaterialRequest;
import br.ufpe.cin.amadeus.amadeus_web.domain.register.Person;

public class MaterialHibernateDAO extends GenericHibernateDAO<Material, Integer> 
		implements MaterialDAO {

	public Material getMaterial(Person person, MaterialRequest materialRequest) {
		
		Criteria crit = getSession().createCriteria(Material.class);
		crit.add(Restrictions.eq("author.id", person.getId()));
		crit.add(Restrictions.eq("requestedMaterial.id", materialRequest.getId()));
		
		return (Material) crit.uniqueResult();
	}

	@Override
	public
	Material getMaterialByID(int material_id){
		
		StringBuilder hql = new StringBuilder();
		hql.append("select m from Material m " +
				   "where m.id = " + material_id);
		
		Material material = (Material) getSession().createQuery(hql.toString()).uniqueResult();
				
		return material;
	}
	
}



