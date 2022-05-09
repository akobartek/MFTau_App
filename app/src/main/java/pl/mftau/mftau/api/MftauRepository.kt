package pl.mftau.mftau.api

import pl.mftau.mftau.model.remote_db.*
import retrofit2.Response

class MftauRepository(private val api: MftauApi) {

    private fun handleApiResponse(response: Response<String>): String? {
        if (response.isSuccessful) return response.body()
        else throw IllegalStateException(response.errorBody()?.toString())
    }


    // region localcommunity
    suspend fun getAllCommunities(): MutableList<LocalCommunity> =
        api.getAllCommunities()

    suspend fun getCommunityById(communityId: Int): LocalCommunity =
        api.getCommunityById(communityId)

    suspend fun addCommunity(community: LocalCommunity): String? =
        handleApiResponse(api.addCommunity(community))

    suspend fun updateCommunity(community: LocalCommunity): String? =
        handleApiResponse(api.updateCommunity(community))

    suspend fun deleteCommunity(communityId: Int): String? =
        handleApiResponse(api.deleteCommunity(communityId))
    // endregion localcommunity

    // region localmembership
    suspend fun getAllMembers(): MutableList<Person> =
        api.getAllMembers()

    suspend fun getCommunityMembers(communityId: Int): LocalCommunity =
        api.getCommunityMembers(communityId)

    suspend fun getPersonMembershipsHistory(personId: Int): MutableList<LocalMembership> =
        api.getPersonMembershipsHistory(personId)

    suspend fun addMembership(membership: LocalMembership): String? =
        handleApiResponse(api.addMembership(membership))

    suspend fun updateMembership(membership: LocalMembership): String? =
        handleApiResponse(api.updateMembership(membership))

    suspend fun deleteMembership(personId: Int, communityId: Int): String? =
        handleApiResponse(api.deleteMembership(personId, communityId))
    // endregion localmembership

    // region person
    suspend fun getAllPersons(): MutableList<Person> =
        api.getAllPersons()

    suspend fun getPersonById(personId: Int): Person =
        api.getPersonById(personId)

    suspend fun addPerson(person: Person): String? =
        handleApiResponse(api.addPerson(person))

    suspend fun updatePerson(person: Person): String? =
        handleApiResponse(api.updatePerson(person))

    suspend fun deletePerson(personId: Int): String? =
        handleApiResponse(api.deletePerson(personId))
    // endregion person

    // region personfunction
    suspend fun getAllPersonsFunctions(): MutableList<PersonFunction> =
        api.getAllPersonsFunctions()

    suspend fun getPersonFunctionById(functionId: Int): PersonFunction =
        api.getPersonFunctionById(functionId)
    // endregion personfunction

    // region personkind
    suspend fun getAllPersonsKinds(): MutableList<PersonKind> =
        api.getAllPersonsKinds()

    suspend fun getPersonKindById(kindId: Int): PersonKind =
        api.getPersonKindById(kindId)
    // endregion personkind

    // region retreat
    suspend fun getAllRetreats(): MutableList<Retreat> =
        api.getAllRetreats()

    suspend fun getRetreatById(retreatId: Int): Retreat =
        api.getRetreatById(retreatId)

    suspend fun addRetreat(retreat: Retreat): String? =
        handleApiResponse(api.addRetreat(retreat))

    suspend fun updateRetreat(retreat: Retreat): String? =
        handleApiResponse(api.updateRetreat(retreat))

    suspend fun deleteRetreat(retreatId: Int): String? =
        handleApiResponse(api.deleteRetreat(retreatId))
    // endregion retreat

    // region retreatparticipation
    suspend fun getRetreatParticipants(retreatId: Int): MutableList<RetreatsParticipation> =
        api.getRetreatParticipants(retreatId)

    suspend fun getPersonRetreatsHistory(personId: Int): MutableList<Retreat> =
        api.getPersonRetreatsHistory(personId)

    suspend fun addSingleParticipation(participation: RetreatsParticipation): String? =
        handleApiResponse(api.addSingleParticipation(participation))

    suspend fun addAllParticipations(participations: List<RetreatsParticipation>): String? =
        handleApiResponse(api.addAllParticipations(participations))

    suspend fun updateParticipation(participation: RetreatsParticipation): String? =
        handleApiResponse(api.updateParticipation(participation))

    suspend fun deleteParticipation(personId: Int, retreatId: Int): String? =
        handleApiResponse(api.deleteParticipation(personId, retreatId))
    // endregion retreatparticipation

    // region retreatparticipationtype
    suspend fun getAllRetreatsParticipationsTypes(): MutableList<RetreatsParticipationType> =
        api.getAllRetreatsParticipationsTypes()

    suspend fun getRetreatParticipationTypeById(typeId: Int): RetreatsParticipationType =
        api.getRetreatParticipationTypeById(typeId)
    // endregion retreatparticipationtype

    // region retreatplace
    suspend fun getAllRetreatsPlaces(): MutableList<RetreatsPlace> =
        api.getAllRetreatsPlaces()

    suspend fun getRetreatsPlaceById(placeId: Int): RetreatsPlace =
        api.getRetreatsPlaceById(placeId)

    suspend fun addPlace(retreatsPlace: RetreatsPlace): String? =
        handleApiResponse(api.addPlace(retreatsPlace))

    suspend fun updatePlace(retreatsPlace: RetreatsPlace): String? =
        handleApiResponse(api.updatePlace(retreatsPlace))

    suspend fun deletePlace(placeId: Int): String? =
        handleApiResponse(api.deletePlace(placeId))
    // endregion retreatplace

    // region retreattargetgroup
    suspend fun getAllRetreatsTargetGroups(): MutableList<RetreatsTargetGroup> =
        api.getAllRetreatsTargetGroups()

    suspend fun getRetreatsTargetGroupById(groupId: Int): RetreatsTargetGroup =
        api.getRetreatsTargetGroupById(groupId)
    // endregion retreattargetgroup

    // region retreattype
    suspend fun getAllRetreatsTypes(): MutableList<RetreatsType> =
        api.getAllRetreatsTypes()

    suspend fun getRetreatsTypeById(typeId: Int): RetreatsType =
        api.getRetreatsTypeById(typeId)
    // endregion retreattype
}