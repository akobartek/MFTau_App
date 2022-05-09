package pl.mftau.mftau.api

import pl.mftau.mftau.model.remote_db.*
import retrofit2.Response
import retrofit2.http.*

interface MftauApi {

    // region localcommunity
    @GET("localcommunity/get.php")
    suspend fun getAllCommunities(): MutableList<LocalCommunity>

    @GET("localcommunity/get.php?id={communityId}")
    suspend fun getCommunityById(@Path("communityId") communityId: Int): LocalCommunity

    @POST("localcommunity/add.php")
    suspend fun addCommunity(@Body community: LocalCommunity): Response<String>

    @PUT("localcommunity/update.php")
    suspend fun updateCommunity(@Body community: LocalCommunity): Response<String>

    @DELETE("localcommunity/delete.php?id={communityId}")
    suspend fun deleteCommunity(@Path("communityId") communityId: Int): Response<String>
    // endregion localcommunity


    // region localmembership
    @GET("localmembership/getMembers.php")
    suspend fun getAllMembers(): MutableList<Person>

    @GET("localmembership/getMembers.php?communityId={communityId}")
    suspend fun getCommunityMembers(@Path("communityId") communityId: Int): LocalCommunity

    @GET("localmembership/getPersonHistory.php?personId={personId}")
    suspend fun getPersonMembershipsHistory(@Path("personId") personId: Int): MutableList<LocalMembership>

    @POST("localmembership/add.php")
    suspend fun addMembership(@Body membership: LocalMembership): Response<String>

    @PUT("localmembership/update.php")
    suspend fun updateMembership(@Body membership: LocalMembership): Response<String>

    @DELETE("localmembership/delete.php?personId={personId}&communityId={communityId}")
    suspend fun deleteMembership(
        @Path("personId") personId: Int,
        @Path("communityId") communityId: Int
    ): Response<String>
    // endregion localmembership


    // region person
    @GET("person/get.php")
    suspend fun getAllPersons(): MutableList<Person>

    @GET("person/get.php?id={personId}")
    suspend fun getPersonById(@Path("personId") personId: Int): Person

    @POST("person/add.php")
    suspend fun addPerson(@Body person: Person): Response<String>

    @PUT("person/update.php")
    suspend fun updatePerson(@Body person: Person): Response<String>

    @DELETE("person/delete.php?id={personId}")
    suspend fun deletePerson(@Path("personId") personId: Int): Response<String>
    // endregion person


    // region personfunction
    @GET("personfunction/get.php")
    suspend fun getAllPersonsFunctions(): MutableList<PersonFunction>

    @GET("personfunction/get.php?id={functionId}")
    suspend fun getPersonFunctionById(@Path("functionId") functionId: Int): PersonFunction
    // endregion personfunction


    // region personkind
    @GET("personkind/get.php")
    suspend fun getAllPersonsKinds(): MutableList<PersonKind>

    @GET("personkind/get.php?id={kindId}")
    suspend fun getPersonKindById(@Path("kindId") kindId: Int): PersonKind
    // endregion personkind


    // region retreat
    @GET("retreat/get.php")
    suspend fun getAllRetreats(): MutableList<Retreat>

    @GET("retreat/get.php?id={retreatId}")
    suspend fun getRetreatById(@Path("retreatId") retreatId: Int): Retreat

    @POST("retreat/add.php")
    suspend fun addRetreat(@Body retreat: Retreat): Response<String>

    @PUT("retreat/update.php")
    suspend fun updateRetreat(@Body retreat: Retreat): Response<String>

    @DELETE("retreat/delete.php?id={retreatId}")
    suspend fun deleteRetreat(@Path("retreatId") retreatId: Int): Response<String>
    // endregion retreat


    // region retreatparticipation
    @GET("retreatparticipation/getRetreatParticipants.php?retreatId={retreatId}")
    suspend fun getRetreatParticipants(@Path("retreatId") retreatId: Int): MutableList<RetreatsParticipation>

    @GET("retreatparticipation/getPersonHistory.php?personId={personId}")
    suspend fun getPersonRetreatsHistory(@Path("personId") personId: Int): MutableList<Retreat>

    @POST("retreatparticipation/add.php")
    suspend fun addSingleParticipation(@Body participation: RetreatsParticipation): Response<String>

    @POST("retreatparticipation/addAll.php")
    suspend fun addAllParticipations(@Body participations: List<RetreatsParticipation>): Response<String>

    @PUT("retreatparticipation/update.php")
    suspend fun updateParticipation(@Body participation: RetreatsParticipation): Response<String>

    @DELETE("retreatparticipation/delete.php?personId=1&retreatId=1")
    suspend fun deleteParticipation(
        @Path("personId") personId: Int,
        @Path("retreatId") retreatId: Int
    ): Response<String>
    // endregion retreatparticipation


    // region retreatparticipationtype
    @GET("retreatparticipationtype/get.php")
    suspend fun getAllRetreatsParticipationsTypes(): MutableList<RetreatsParticipationType>

    @GET("retreatparticipationtype/get.php?id={typeId}")
    suspend fun getRetreatParticipationTypeById(@Path("typeId") typeId: Int): RetreatsParticipationType
    // endregion retreatparticipationtype


    // region retreatplace
    @GET("retreatplace/get.php")
    suspend fun getAllRetreatsPlaces(): MutableList<RetreatsPlace>

    @GET("retreatplace/get.php?id={placeId}")
    suspend fun getRetreatsPlaceById(@Path("placeId") placeId: Int): RetreatsPlace

    @POST("retreatplace/add.php")
    suspend fun addPlace(@Body retreatsPlace: RetreatsPlace): Response<String>

    @PUT("retreatplace/update.php")
    suspend fun updatePlace(@Body retreatsPlace: RetreatsPlace): Response<String>

    @DELETE("retreatplace/delete.php?id={placeId}")
    suspend fun deletePlace(@Path("placeId") placeId: Int): Response<String>
    // endregion retreatplace


    // region retreattargetgroup
    @GET("retreattargetgroup/get.php")
    suspend fun getAllRetreatsTargetGroups(): MutableList<RetreatsTargetGroup>

    @GET("retreattargetgroup/get.php?id={groupId}")
    suspend fun getRetreatsTargetGroupById(@Path("groupId") groupId: Int): RetreatsTargetGroup
    // endregion retreattargetgroup


    // region retreattype
    @GET("retreattype/get.php")
    suspend fun getAllRetreatsTypes(): MutableList<RetreatsType>

    @GET("retreattype/get.php?id={typeId}")
    suspend fun getRetreatsTypeById(@Path("typeId") typeId: Int): RetreatsType
    // endregion retreattype
}