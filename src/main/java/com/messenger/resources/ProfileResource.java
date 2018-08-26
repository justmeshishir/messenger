package com.messenger.resources;

import com.messenger.model.Profile;
import com.messenger.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON) //Accepts JSON Data
@Produces(MediaType.APPLICATION_JSON) //Returns JSON Data
public class ProfileResource {

    private ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles(){
        return profileService.getAllProfiles();
    }

    @POST
    public Profile addProfile(Profile profile){
        return profileService.addProfile(profile);
    }

    @GET
    @Path("{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName){
        return profileService.getProfile(profileName);
    }

    @PUT
    @Path("{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile){
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("{profileName}")
    public void deleteProfile(@PathParam("profileName") String profileName){
        profileService.removeProfile(profileName);
    }

}
