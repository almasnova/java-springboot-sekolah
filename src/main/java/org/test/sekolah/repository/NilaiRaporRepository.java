
package org.test.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.sekolah.entity.NilaiRapor;

@Repository
public interface NilaiRaporRepository extends JpaRepository<NilaiRapor, Long> {
}
