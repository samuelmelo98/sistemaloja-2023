package repositorios;

 interface BaseDados<T>{
	Boolean containd(T o);
	Boolean adicionar(T o);
	Boolean remover(T o);
	Boolean atualizar(T o);
 
	 
		
	
	
}
