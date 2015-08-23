	function localizarPorCargo(){
		
		var id = document.getElementById("cargo");
		var value = id.options[id.selectedIndex].value;
		
		if(value == ''){
			window.location = 'http://localhost:8080/app-final/funcionario/add';
		}else{
			window.location = 'http://localhost:8080/app-final/funcionario/find/cargo/'+value;
		}
		
	}
	
	function localizaPorNome(){
	
		var value = document.getElementById("nomea").value;
	
		if(value == ''){
			window.location = 'http://localhost:8080/app-final/funcionario/add';
		}else{
			window.location = 'http://localhost:8080/app-final/funcionario/find/nome/'+value;
		}
	
	}
