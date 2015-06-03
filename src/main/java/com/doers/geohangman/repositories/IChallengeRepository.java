package com.doers.geohangman.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doers.geohangman.model.entities.Challenge;

/**
 * Challenge repository interface
 * 
 * @author @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 *
 */
public interface IChallengeRepository extends
		CrudRepository<Challenge, Integer> {

	/**
	 * Finds a Challenge by Challenger Id
	 * 
	 * @param id
	 *            The user id
	 * @return List of user challenges
	 */
	List<Challenge> findByChallengerId(Integer id);

	/**
	 * Finds a Challenge by Opponent Id
	 * 
	 * @param id
	 *            The user id
	 * @return List of user challenges in which he has been an opponent
	 */
	List<Challenge> findByOpponentId(Integer id);

	/**
	 * Given the challengerId and the opponentId, retrieve all challenges where
	 * challengerId and OpponentId have participated
	 * 
	 * @param challengerId
	 *            the user Id
	 * @param opponentId
	 *            the user Id
	 * @return List of all challenges where challengerId and OpponentId have
	 *         participated
	 */
	@Query("SELECT c FROM Challenge c WHERE c.challengerId = :challengerId AND c.opponentId = :opponentId")
	List<Challenge> findByChallengerIdAndOpponentId(
			@Param("challengerId") Integer challengerId,
			@Param("opponentId") Integer opponentId);

}
