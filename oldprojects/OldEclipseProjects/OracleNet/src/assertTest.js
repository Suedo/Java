function useless (callback) {
	return callback(); //function gets invoked
}

var text = "domo arigato!";

// in assert method provided by the Console , the string after the comma gets displayed if assertion fails
assert ( useless( function(){ return text;  } ) === text , 
		"the useless function is pretty nifty !" + text); 
		
/*
	in the definition of useless() , the thing that gets passed to the function gets invoked inside it.
	thus, in the assert statement , we readily create a new anonymous function where the passed argument should be,
	effectively passing this newly created function to the function useless(). and useless , by definition , invokes it.
*/		