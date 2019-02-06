package com.example.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import com.example.model.Random;

import java.util.LinkedList;
import java.util.List;

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
      throw new IllegalArgumentException("there are no random numbers at the moment");
    }
    //do some slow stuff to find the number
    return randomNumbers.removeFirst();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Random getById(@PathParam("id") Long id) {
	  //linear search
	  for (Random random : randomNumbers) {
		if(random.getId().equals(id)) {
			return random;
		}
	  }
	  return null;
  }
/*
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void addRandomNumber(Random random) {
	  System.out.println("Random number added: " + random.getRandom());
	  randomNumbers.add(random);
  }
  */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public void addRandomNumbers(List<Random> randomNumbers) {
	  for(Random random: randomNumbers) {
		  System.out.println("Random number added: " + random.getRandom());
		  this.randomNumbers.add(random);
	  }
  }
}

