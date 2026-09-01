package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cadet;

@Repository
@Transactional
public interface AdminCadetRepo extends JpaRepository<Cadet, Long>, PagingAndSortingRepository<Cadet, Long> {

	Page<Cadet> findAllByStatusOrderByIdDesc(Integer one, Pageable paginationData);

	Cadet findByServiceId(String serviceId);

	Page<Cadet> findAllByOrderByIdDesc(Pageable paginationData);

	List<Cadet> findAllByOrderByIdDesc();

	Cadet findByUsernameAndStatus(String username, Integer one);

	Cadet findByUsernameAndStatusAndBattalian(String username, Integer one, String name);

	List<Cadet> findByBattalianOrderByIdDesc(String status);

	Cadet findByServiceIdAndTerm(String serviceId, Long termId);

	List<Cadet> findAllByTermAndBattalianAndCompany(Long termId, String battalion, String company,
			Pageable paginationData);

	List<Cadet> findAllByTermAndBattalian(Long termId, String battalion, Pageable paginationData);

	List<Cadet> findAllByTerm(Long termId, Pageable paginationData);

	@Override
	Page<Cadet> findAll(Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndServiceIdLike(Long termId, String battalion, String company,
			String string, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndServiceIdLike(Long termId, String battalion, String string,
			Pageable pageable);

	List<Cadet> findAllByTermAndServiceIdLike(Long termId, String string, Pageable pageable);

	List<Cadet> findByServiceIdLike(String string, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompany(Long termId, String battalion, String company);

	List<Cadet> findAllByTermAndBattalian(Long termId, String battalion);

	List<Cadet> findAllByTerm(Long termId);

	List<Cadet> findByServiceIdLike(String string);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndServiceIdLike(Long termId, String battalion, String company,
			String string);

	List<Cadet> findAllByTermAndBattalianAndServiceIdLike(Long termId, String battalion, String string);

	List<Cadet> findAllByTermAndServiceIdLike(Long termId, String string);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSessionAndServiceIdLike(Long termId, String battalion,
			String company, String termSession, String string, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSessionAndServiceIdLike(Long termId, String battalion,
			String company, String termSession, String string);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSession(Long termId, String battalion, String company,
			String termSession, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSession(Long termId, String battalion, String company,
			String termSession);

	List<Cadet> findAllByTermAndBattalianAndTermSessionAndServiceIdLike(Long termId, String battalion,
			String termSession, String string, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndTermSessionAndServiceIdLike(Long termId, String battalion,
			String termSession, String string);

	List<Cadet> findAllByTermAndBattalianAndTermSession(Long termId, String battalion, String termSession,
			Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndTermSession(Long termId, String battalion, String termSession);

	List<Cadet> findAllByTermAndTermSessionAndServiceIdLike(Long termId, String termSession, String string,
			Pageable pageable);

	List<Cadet> findAllByTermAndTermSessionAndServiceIdLike(Long termId, String termSession, String string);

	List<Cadet> findAllByTermAndTermSession(Long termId, String termSession, Pageable pageable);

	List<Cadet> findAllByTermAndTermSession(Long termId, String termSession);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndStatus(Long termId, String battalion, String company, int i,
			Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndStatus(Long termId, String battalion, String company, int i);

	List<Cadet> findAllByTermAndBattalianAndStatus(Long termId, String battalion, int i, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndStatus(Long termId, String battalion, int i);

	List<Cadet> findAllByTermAndStatus(Long termId, int i, Pageable pageable);

	List<Cadet> findAllByTermAndStatus(Long termId, int i);

	List<Cadet> findAllByStatus(int i, Pageable pageable);

	List<Cadet> findAllByStatus(int i);

	List<Cadet> findByStatusAndServiceIdLike(int i, String string, Pageable pageable);

	List<Cadet> findByStatusAndServiceIdLike(int i, String string);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndStatusAndServiceIdLike(Long termId, String battalion,
			String company, int i, String string, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndStatusAndServiceIdLike(Long termId, String battalion,
			String company, int i, String string);

	List<Cadet> findAllByTermAndBattalianAndStatusAndServiceIdLike(Long termId, String battalion, int i, String string,
			Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndStatusAndServiceIdLike(Long termId, String battalion, int i, String string);

	List<Cadet> findAllByTermAndStatusAndServiceIdLike(Long termId, int i, String string, Pageable pageable);

	List<Cadet> findAllByTermAndStatusAndServiceIdLike(Long termId, int i, String string);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSessionAndStatusAndServiceIdLike(Long termId,
			String battalion, String company, String termSession, int i, String string, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSessionAndStatusAndServiceIdLike(Long termId,
			String battalion, String company, String termSession, int i, String string);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSessionAndStatus(Long termId, String battalion,
			String company, String termSession, int i, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndTermSessionAndStatus(Long termId, String battalion,
			String company, String termSession, int i);

	List<Cadet> findAllByTermAndBattalianAndTermSessionAndStatusAndServiceIdLike(Long termId, String battalion,
			String termSession, int i, String string, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndTermSessionAndStatusAndServiceIdLike(Long termId, String battalion,
			String termSession, int i, String string);

	List<Cadet> findAllByTermAndBattalianAndTermSessionAndStatus(Long termId, String battalion, String termSession,
			int i, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndTermSessionAndStatus(Long termId, String battalion, String termSession,
			int i);

	List<Cadet> findAllByTermAndTermSessionAndStatusAndServiceIdLike(Long termId, String termSession, int i,
			String string, Pageable pageable);

	List<Cadet> findAllByTermAndTermSessionAndStatusAndServiceIdLike(Long termId, String termSession, int i,
			String string);

	List<Cadet> findAllByTermAndTermSessionAndStatus(Long termId, String termSession, int i, Pageable pageable);

	List<Cadet> findAllByTermAndTermSessionAndStatus(Long termId, String termSession, int i);

	List<Cadet> findAllByBattalianAndCompanyAndStatus(String battalion, String company, int i, Pageable pageable);

	List<Cadet> findAllByBattalianAndCompanyAndStatus(String battalion, String company, int i);

	List<Cadet> findAllByBattalianAndStatus(String battalion, int i, Pageable pageable);

	List<Cadet> findAllByBattalianAndStatus(String battalion, int i);

	List<Cadet> findAllByStatusNotInOrderByIdDesc(Integer[] deletedStatus);

	Cadet findByServiceIdAndStatusNotIn(String serviceId, Integer[] deletedStatus);

	List<Cadet> findByBattalianAndStatusNotInOrderByIdDesc(String status, Integer[] deletedStatus);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndStatusNotIn(Long termId, String battalion, String company,
			Integer[] deletedStatus, Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndCompanyAndStatusNotIn(Long termId, String battalion, String company,
			Integer[] deletedStatus);

	List<Cadet> findAllByTermAndBattalianAndStatusNotIn(Long termId, String battalion, Integer[] deletedStatus,
			Pageable pageable);

	List<Cadet> findAllByTermAndBattalianAndStatusNotIn(Long termId, String battalion, Integer[] deletedStatus);

	List<Cadet> findAllByTermAndStatusNotIn(Long termId, Integer[] deletedStatus, Pageable pageable);

	List<Cadet> findAllByTermAndStatusNotIn(Long termId, Integer[] deletedStatus);

	List<Cadet> findAllByBattalianAndCompanyAndStatusNotIn(String battalion, String company, Integer[] deletedStatus,
			Pageable pageable);

	List<Cadet> findAllByBattalianAndCompanyAndStatusNotIn(String battalion, String company, Integer[] deletedStatus);

	List<Cadet> findAllByBattalianAndStatusNotIn(String battalion, Integer[] deletedStatus, Pageable pageable);

	List<Cadet> findAllByBattalianAndStatusNotIn(String battalion, Integer[] deletedStatus);

	List<Cadet> findAllByStatusNotIn(Integer[] deletedStatus, Pageable pageable);

	List<Cadet> findAllByStatusNotIn(Integer[] deletedStatus);

	List<Cadet> findByStatusNotInAndServiceIdLike(Integer[] deletedStatus, String string, Pageable pageable);

	List<Cadet> findByStatusNotInAndServiceIdLike(Integer[] deletedStatus, String string);

}
