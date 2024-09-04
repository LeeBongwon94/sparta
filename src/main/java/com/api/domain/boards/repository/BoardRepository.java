package com.api.domain.boards.repository;

import com.api.domain.boards.entity.Board;
import com.api.domain.boards.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByMemberId(Member memberId, Pageable pageable);
    Page<Board> findByMemberIdIn(List<Member> memberId, Pageable pageable);

    Optional<Board> findByMemberIdAndBoardId(Member memberId, Long boardId);

    List<Board> findAllByMemberId(Member memberId);
}

