package com.huskycode.jpaquery.persister;

import com.huskycode.jpaquery.DependenciesDefinition;
import com.huskycode.jpaquery.populator.RandomValuePopulator;
import com.huskycode.jpaquery.testmodel.ClassA;
import com.huskycode.jpaquery.testmodel.pizza.deps.PizzaDeps;
import com.huskycode.jpaquery.types.tree.CreationPlan;
import com.huskycode.jpaquery.types.tree.PersistedResult;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PersisterImplTest {
	private PersisterImpl persister;
	private BeanCreator beanCreator;
	private RandomValuePopulator randomValuePopulator;
	private EntityManager em;
	private DependenciesDefinition deps;

	@Before
	public void before() {
	    em = Mockito.mock(EntityManager.class);
		beanCreator = new BeanCreator();
		randomValuePopulator = Mockito.mock(RandomValuePopulator.class);
		deps = new PizzaDeps().getDepsUsingField();
		
	    persister = new PersisterImpl(em, beanCreator, randomValuePopulator, deps);
	}
	
	@Test
	public void testPersistValuePersistTheRootFromCreationTree() {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(ClassA.class) ;
		CreationPlan plan = CreationPlan.newInstance(classes);
		
		PersistedResult persistedTree = persister.persistValues(plan);
		assertThat(persistedTree.getPersistedObjects().size(), is(1));
		assertThat(persistedTree.getPersistedObjects().get(0), CoreMatchers.instanceOf(ClassA.class));
	}
	
	@Test
	public void testPersistValueCallsRandomValue() throws IllegalAccessException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(ClassA.class) ;
        CreationPlan plan = CreationPlan.newInstance(classes);
		
		persister.persistValues(plan);
		
		Mockito.verify(randomValuePopulator, Mockito.times(1)).populateValue(Mockito.any(ClassA.class));
	}
	
	@Test
	public void testPersistValuePersistAGivenClass() throws IllegalAccessException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        classes.add(ClassA.class) ;
        CreationPlan plan = CreationPlan.newInstance(classes);
		
		persister.persistValues(plan);
		
		verify(em, times(1)).persist(Mockito.any(ClassA.class));
	}
}
