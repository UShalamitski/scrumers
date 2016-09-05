<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

    <footer class="text-center">
        <div class="footer-above">
            <div class="container">
                <div class="row">
                    <div class="footer-col col-md-4">
                        <h3><spring:message code="footer.location" text="location" /></h3>
                        <p><spring:message code="footer.address1" text="3481 Melrose Place" />
                            <br><spring:message code="footer.address2" text="Beverly Hills, CA 90210" />
                        </p>
                    </div>
                    <div class="footer-col col-md-4">
                        <h3><spring:message code="footer.around" text="Around the Web" /></h3>
                        <ul class="list-inline">
                            <li><a href="#" class="btn-social btn-outline" style="pointer-events: none;"><i class="fa fa-fw fa-facebook"></i></a></li>
                            <li><a href="#" class="btn-social btn-outline" style="pointer-events: none;"><i class="fa fa-fw fa-google-plus"></i></a></li>
                            <li><a href="#" class="btn-social btn-outline" style="pointer-events: none;"><i class="fa fa-fw fa-twitter"></i></a></li>
                            <li><a href="#" class="btn-social btn-outline" style="pointer-events: none;"><i class="fa fa-fw fa-linkedin"></i></a></li>
                            <li><a href="#" class="btn-social btn-outline" style="pointer-events: none;"><i class="fa fa-fw fa-dribbble"></i></a></li>
                        </ul>
                    </div>
                    <div class="footer-col col-md-4">
                        <h3><spring:message code="footer.aboutNetradio" text="About Netradio" /></h3>
                        <p><spring:message code="footer.aboutus" text="About us" /></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <spring:message code="footer.copyrigth" text="Copyright &copy; 2014 - Your Name" />
                    </div>
                </div>
            </div>
        </div>
    </footer>