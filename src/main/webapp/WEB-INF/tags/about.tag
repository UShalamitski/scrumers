<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

    <section class="success" id="about">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h1><spring:message code="about.about" text="About"/></h1>
                    <hr class="star-light">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-lg-offset-2">
                    <p class="wow fadeInLeft"><spring:message code="about.about1" text="About"/></p>
                </div>
                <div class="col-lg-4">
                    <p class="wow fadeInRight"><spring:message code="about.about2" text="About"/></p>
                </div>
            </div>
        </div>
    </section>