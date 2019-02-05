package com.example.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.model.Random;

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("/random")
@Singleton
public class RandomEndpoint {
  private LinkedList<Random> randomNumbers;
  
  public RandomEndpoint() {
	  randomNumbers = new LinkedList<Random>();
  }
  
  @GET
  @Produces("application/json")
  public Random doGet() {
    if(randomNumbers.size() == 0) {
      throw new IllegalArgumentException("there are no random numbers t the moment");
    }
    //do some slow stuff to find the number
    return randomNumbers.removeFirst();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Random getById(@PathParam("id") Long id) {
	  //sleep random time to make monitoring more interesting
	  try {
		  Thread.sleep((long) (Math.random() * 2000));
	  } catch (InterruptedException e) {
		  e.printStackTrace();
	  }
	  
	  //linear search
	  for (Random random : randomNumbers) {
		if(random.getId().equals(id)) {
			return random;
		}
	  }
	  return null;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void addRandomNumber(Random random) {
	  System.out.println("Random number added: " + random.getRandom());
	  randomNumbers.add(random);
  }
}

