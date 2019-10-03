package com.spqr.graphql.springbootspqrgraphql.service;

import com.spqr.graphql.springbootspqrgraphql.bean.Article;
import com.spqr.graphql.springbootspqrgraphql.bean.Comment;
import com.spqr.graphql.springbootspqrgraphql.bean.Profile;
import com.spqr.graphql.springbootspqrgraphql.repository.ArticleRepository;
import com.spqr.graphql.springbootspqrgraphql.repository.CommentRepository;
import com.spqr.graphql.springbootspqrgraphql.repository.ProfileRepository;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.relay.Page;
import io.leangen.graphql.execution.relay.generic.PageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author ankushnakaskar
 */
@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Page<Article> articles(@GraphQLArgument(name = "first") int first, @GraphQLArgument(name = "after",defaultValue = "1") String after) {
        log.info("Fetching all the articles ....!!!!");
        Long offset = Long.parseLong(after);
        final List<Article> pages = articleRepository.findByIdGreaterThan(offset, PageRequest.of(0, first));
        Page<Article> bookPage = PageFactory.createOffsetBasedPage(pages, articleRepository.count(), offset);
        return bookPage;
    }

    @GraphQLQuery
    public Profile author(@GraphQLContext Article article) {
        log.info("Fetching the profile for article..." + article);
        return profileRepository.findById(article.getAuthorId()).get();
    }

    @GraphQLQuery
    public List<Comment> getComments(@GraphQLContext Article article) {
        log.info("Fetching the comments for article..." + article);
        return commentRepository.findByArticleId(article.getId());
    }

    @GraphQLQuery(name = "article")
    public Optional<Article> getArticle(@GraphQLArgument(name = "articleId") Long articleId) {
        return articleRepository.findById(articleId);
    }

}
