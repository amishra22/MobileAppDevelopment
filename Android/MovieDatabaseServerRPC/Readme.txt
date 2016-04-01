To run the server on windows :

1) Open command promt and go to JSONRPCServer directory.
2) Since the project is already built me might not need to build it again. (In case to build it again run "ant build.java.server" from within JSONRPCServer directory.
3) Go to classes directory and execute the command :
	java -cp ..\lib\json.jar; ser598/server/amishr22/MovieServer 8080
4) To check whether the server is running properly execute below curl command and one should see the desired output:
	
	a) Details of movie Fight Club : "curl --data "{ \"jsonrpc\": \"2.0\", \"method\": \"get\", \"params\": [\"Fight Club\"], \"id\": 3}" localhost:8080"
	
	OUTPUT : {"result":{"Runtime":"139 min","Released":"15 Oct 1999","Year":"1999","Rated":"R","Plot":"An insomniac office worker, looking for a way to change his life, crosses paths with a devil-may-care soap maker, forming an underground fight club that evolves into something much, much more...","Title":"Fight Club","Actors":"Edward Norton, Brad Pitt, Helena Bonham Carter, Meat Loaf","Genre":"Adventure"},"id":3,"jsonrpc":"2.0"}
	
	b)List of movies based on genre list : "curl --data "{ \"jsonrpc\": \"2.0\", \"method\": \"getMovieList\", \"params\": [ ], \"id\": 3}" localhost:8080"
	
	OUTPUT : {"result":{"Action":["Kung Fu Panda","Interstellar","drishyam"],"Adventure":["abc","Fight Club"],"Drama":["chiggy qiggy","The Shawshank Redemption","Forrest Gump","Casablanca"],"Animation":["How to Train Your Dragon"],"Comedy":["Intern","The Ugly Truth","50 First Dates"]},"id":3,"jsonrpc":"2.0"}
	

To run on unix type env:

1) After building the project execute below command

	java -cp classes:lib/json.jar ser598.server.amishr22.MovieServer 8080