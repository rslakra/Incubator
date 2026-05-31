//package com.rslakra.jdksuite.persistence;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
///**
// * @author Rohtash Lakra
// * @version 1.0.0
// * @since 05/30/2026 6:53 PM
// */
//public class OrgnanizationModelListner extends BaseModelListener<Organization> {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(OrgnanizationModelListner.class);
//
//    public OrgnanizationModelListner() {
//        LOGGER.debug("OrgnanizationModelListner.OrgnanizationModelListner()");
//    }
//
//    @Override
//    public void onBeforeCreate(Organization model) throws ModelListenerException {
//        LOGGER.debug("About to create model: " + model.getPrimaryKey());
//    }
//
//    @Override
//    public void onAfterRemove(Organization org) throws ModelListenerException {
//        Thread thread4=new Thread() {
//            public synchronized void run() {
//                LOGGER.debug("OrgnanizationModelListner.onAfterRemove()");
//
//
//                LOGGER.info("finding the organization mapping after remove the organization");
//                LOGGER.info("finding RoleLandingPage table mapping--");
//                try {
//                    List<RoleLandingPage>   landingPages  =RoleLandingPageLocalServiceUtil.getRoleLandingPagesBasedOnOrganizationId(org.getOrganizationId());
//                    if(Validator.isNotNull(landingPages))
//                    {
//                        for(RoleLandingPage landingPage:landingPages)
//                        {
//                            RoleLandingPageLocalServiceUtil.deleteRoleLandingPage(landingPage);
//                            LOGGER.info("RoleLandingPage mapping organizationType_OrgId="+landingPage.getId()+"  removed, OrganizationId "+org.getOrganizationId());
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting RoleLandingPage :"+e.getMessage());
//                }
//                LOGGER.info("finding Org_Roles table mapping--");
//
//
//
//                LOGGER.info("finding User_Default_SakaiTools table mapping--");
//                try {
//                    DynamicQuery dynamicQueryUDS	= User_Default_SakaiToolsLocalServiceUtil.dynamicQuery();
//                    dynamicQueryUDS.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<User_Default_SakaiTools> default_SakaiTools= User_Default_SakaiToolsLocalServiceUtil.dynamicQuery(dynamicQueryUDS);
//                    LOGGER.info("default_SakaiTools :"+default_SakaiTools);
//                    if(Validator.isNotNull(default_SakaiTools))
//                    {
//                        for(User_Default_SakaiTools  default_SakaiTool:default_SakaiTools)
//                        {
//                            default_SakaiTool=User_Default_SakaiToolsLocalServiceUtil.deleteUser_Default_SakaiTools(default_SakaiTool);
//                            LOGGER.info("Org_Roles mapping  removed, OrganizationId "+org.getOrganizationId()+"  removed default_SakaiTool :"+default_SakaiTool);
//
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting User_Default_SakaiTools is"+e.getMessage());
//                }
//
//                LOGGER.info("finding Users_Associations table mapping--");
//                try {
//                    DynamicQuery dynamicQueryUAss	= Users_AssociationsLocalServiceUtil.dynamicQuery();
//                    dynamicQueryUAss.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<Users_Associations> users_Associations= Users_AssociationsLocalServiceUtil.dynamicQuery(dynamicQueryUAss);
//                    LOGGER.info("List of users_Associations is :"+users_Associations);
//                    if(Validator.isNotNull(users_Associations))
//                    {
//                        for(Users_Associations  users_Association:users_Associations)
//                        {
//                            Users_AssociationsLocalServiceUtil.deleteUsers_Associations(users_Association);
//                            LOGGER.info("Users_Associations id="+users_Association.getUaId()+" mapping  removed, OrganizationId "+org.getOrganizationId());
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting Users_Associations is :"+e.getMessage());
//                }
//
//                LOGGER.info("finding UserAssociatesActivities table mapping--");
//                try {
//                    DynamicQuery dynamicQueryUserAssociatesActivities	= UserAssociatesActivitiesLocalServiceUtil.dynamicQuery();
//                    dynamicQueryUserAssociatesActivities.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<UserAssociatesActivities> userAssociatesActivitis= UserAssociatesActivitiesLocalServiceUtil.dynamicQuery(dynamicQueryUserAssociatesActivities);
//                    LOGGER.info("List of userAssociatesActivitis is :"+userAssociatesActivitis);
//                    if(Validator.isNotNull(userAssociatesActivitis))
//                    {
//                        for(UserAssociatesActivities userAssociatesActivitie:userAssociatesActivitis)
//                        {
//                            UserAssociatesActivitiesLocalServiceUtil.deleteUserAssociatesActivities(userAssociatesActivitie);
//                            LOGGER.info("UserAssociatesActivities id="+userAssociatesActivitie.getUaId()+" mapping  removed, OrganizationId "+org.getOrganizationId());
//
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting UserAssociatesActivities is "+e.getMessage());
//                }
//
//                LOGGER.info("finding  Organization_Model table mapping--");
//                try {
//                    Organization_Model organization_Model=  Organization_ModelLocalServiceUtil.findByorganizationId(org.getOrganizationId(), 1);
//                    LOGGER.info("organization_Model is :"+organization_Model);
//                    if(Validator.isNotNull(organization_Model))
//                    {
//                        organization_Model=Organization_ModelLocalServiceUtil.deleteOrganization_Model(organization_Model);
//                    }
//                    LOGGER.info("Organization_Model id="+organization_Model.getId_()+" mapping  removed, OrganizationId "+org.getOrganizationId());
//
//                } catch (Exception e) {
//                    LOGGER.error("exception raised while deleting organization_Model"+e.getMessage());
//                }
//            }
//        };
//        thread4.start();
//
//
//        LOGGER.debug("*************cache clear , commit code************");
//
//
//        super.onAfterRemove(org);
//    }
//
//    @Override
//    public void onBeforeRemove(Organization org) throws ModelListenerException {
//        // TODO Auto-generated method stub
//        LOGGER.debug("*********OrgnanizationModelListner.onBeforeRemove()***************");
//        LOGGER.debug("**********Main Thread name :"+Thread.currentThread().getName());
//
//
//        Thread.UncaughtExceptionHandler exceptionHandler=new Thread.UncaughtExceptionHandler() {
//            public void uncaughtException(Thread th, Throwable ex) {
//                LOGGER.debug("Uncaught exception: " + ex);
//            }
//        };
//
//        Thread thread=new Thread() {
//            public synchronized void run() {
//                LOGGER.debug("new thread created--run() exceuted");
//                LOGGER.debug("**********Thread name :"+Thread.currentThread().getName());
//
//                try {
//                    OrganizationType_Org organizationType_Org=  OrganizationType_OrgLocalServiceUtil.findByOrganizationId(org.getOrganizationId());
//                    LOGGER.info("organizationType_Org :"+organizationType_Org);
//                    if(Validator.isNotNull(organizationType_Org))
//                    {
//                        organizationType_Org=OrganizationType_OrgLocalServiceUtil.deleteOrganizationType_Org(organizationType_Org);
//                        LOGGER.info("removed organizationType_Org is "+organizationType_Org);
//                        LOGGER.info("removed OrganizationType_Org mapping with  organizationType_OrgId="+organizationType_Org.getOrganizationTypeId()+" OrganizationId "+org.getOrganizationId()  );
//                    }
//
//                } catch (NoSuchOrganizationType_OrgException e) {
//                    LOGGER.error("Exeception Ocures while deleting the mapping with  Organization to OrganizationType_Org "+e.getMessage());
//                } catch (SystemException e) {
//                    LOGGER.error("Exeception Ocures while deleting the mapping with  Organization to OrganizationType_Org "+e.getMessage());
//                }
//
//                try {
//                    List<Org_Roles>  org_Roles=  Org_RolesLocalServiceUtil.findByOrgId(org.getOrganizationId());
//                    LOGGER.info("List org_Roles is :"+org_Roles);
//                    if(Validator.isNotNull(org_Roles))
//                    {
//                        for(Org_Roles org_Role:org_Roles)
//                        {
//                            Org_RolesLocalServiceUtil.deleteOrg_Roles(org_Role);
//                            LOGGER.info("removed Org_Roles mapping Org_RolesId="+org_Role.getOsrId()+" , OrganizationId "+org.getOrganizationId());
//
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting Org_Roles is "+e.getMessage() );
//                }
//
//                LOGGER.info("finding Org_RegularRole table mapping--");
//                try {
//                    Org_RegularRole  org_RegularRole =  Org_RegularRoleLocalServiceUtil.findByOrganizationId(org.getOrganizationId());
//                    LOGGER.info("org_RegularRole is :"+org_RegularRole);
//                    if(Validator.isNotNull(org_RegularRole)) {
//                        Org_RegularRoleLocalServiceUtil.deleteOrg_RegularRole(org_RegularRole);
//                    }
//                } catch (NoSuchOrg_RegularRoleException e) {
//                    LOGGER.error("Exeception Ocures while deleting the mapping with  Organization to Org_RegularRole "+e.getMessage());
//                } catch (SystemException e) {
//                    LOGGER.error("Exeception Ocures while deleting the mapping with  Organization to Org_RegularRole "+e.getMessage());
//                }catch (Exception e) {
//                    LOGGER.error("Exeception Ocures while deleting the mapping with  Organization to Org_RegularRole "+e.getMessage());
//                }
//
//                //1
//                try {
//                    Organization_Ext organization_Ext=Organization_ExtLocalServiceUtil.findByOrganizationId(org.getOrganizationId());
//                    LOGGER.info("organization_Ext :"+organization_Ext);
//                    if(Validator.isNotNull(organization_Ext)) {
//                        organization_Ext=Organization_ExtLocalServiceUtil.deleteOrganization_Ext(organization_Ext);
//                        LOGGER.info("removed Organization_Ext is :"+organization_Ext);
//                    }
//                } catch (NoSuchOrganization_ExtException | SystemException e) {
//                    LOGGER.error("exception occured while deleting the organization_Ext :"+e.getMessage());
//                }
//
//                //3
//                try {
//                    DynamicQuery dynamicQueryOASS=Org_Artifact_Site_SentMailsInfoLocalServiceUtil.dynamicQuery();
//                    dynamicQueryOASS.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<Org_Artifact_Site_SentMailsInfo> listOASS=Org_Artifact_Site_SentMailsInfoLocalServiceUtil.dynamicQuery(dynamicQueryOASS);
//                    LOGGER.info("listOASS :"+listOASS);
//                    if(Validator.isNotNull(listOASS)) {
//                        for(Org_Artifact_Site_SentMailsInfo org_Artifact_Site_SentMailsInfo : listOASS) {
//                            org_Artifact_Site_SentMailsInfo=Org_Artifact_Site_SentMailsInfoLocalServiceUtil.deleteOrg_Artifact_Site_SentMailsInfo(org_Artifact_Site_SentMailsInfo);
//                            LOGGER.info("Org_Artifact_Site_SentMailsInfoLocalServiceUtil removed , OrganizationId "+org.getOrganizationId());
//                        }
//                    }
//                }catch (Exception e) {
//                    LOGGER.error("exception raised while deleting Org_Artifact_Site_SentMailsInfo is :"+e.getMessage());
//                }
//
//                //4
//                try {
//                    DynamicQuery dynamicQueryOrgArtifactSiteTemplates=Org_Artifact_Site_TemplatesLocalServiceUtil.dynamicQuery();
//                    dynamicQueryOrgArtifactSiteTemplates.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<Org_Artifact_Site_Templates> listOfOrg_Artifact_Site_Templates=Org_Artifact_Site_TemplatesLocalServiceUtil.dynamicQuery(dynamicQueryOrgArtifactSiteTemplates);
//                    LOGGER.info("listOfOrg_Artifact_Site_Templates :"+listOfOrg_Artifact_Site_Templates);
//                    if(Validator.isNotNull(listOfOrg_Artifact_Site_Templates)) {
//                        for(Org_Artifact_Site_Templates org_Artifact_Site_Templates:listOfOrg_Artifact_Site_Templates) {
//                            org_Artifact_Site_Templates=Org_Artifact_Site_TemplatesLocalServiceUtil.deleteOrg_Artifact_Site_Templates(org_Artifact_Site_Templates);
//                            LOGGER.error("removed Org_Artifact_Site_Templates is "+org_Artifact_Site_Templates);
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting Org_Artifact_Site_Templates is "+e.getMessage());
//                }
//            }
//        };
//        thread.setUncaughtExceptionHandler(exceptionHandler);
//        LOGGER.info("start method called");
//        thread.start();
//
//        Thread thread2=new Thread() {
//
//            public synchronized void run() {
//                LOGGER.debug("Thread2 name is :"+Thread.currentThread().getName());
//
//                /* AttributeArchitecture service builder*/
//                //6
//                try {
//                    LOGGER.info("finding mapping Team_Hierarchy table mapping --" );
//                    DynamicQuery dynamicQueryTH=Team_HierarchyLocalServiceUtil.dynamicQuery();
//                    dynamicQueryTH.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<Team_Hierarchy> listTH=Team_HierarchyLocalServiceUtil.dynamicQuery(dynamicQueryTH);
//                    LOGGER.info("listTH :"+listTH);
//                    if(Validator.isNotNull(listTH)) {
//
//                        for(Team_Hierarchy team_Hierarchy : listTH) {
//                            Team_HierarchyLocalServiceUtil.deleteTeam_Hierarchy(team_Hierarchy);
//                            LOGGER.info("team_Hierarchy removed, OrganizationId "+org.getOrganizationId());
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting Team_Hierarchy is "+e.getMessage());
//                }
//
//
//                //7
//                try {
//                    List<Attribute> listOfAttribute=AttributeLocalServiceUtil.findByorgAttributes(org.getOrganizationId());
//                    LOGGER.info("listOfAttribute :"+listOfAttribute);
//                    if(Validator.isNotNull(listOfAttribute)) {
//                        for(Attribute attributes : listOfAttribute) {
//
//                            Attribute attribute=AttributeLocalServiceUtil.deleteAttribute(attributes);
//                            LOGGER.info("removed attribute table , OrganizationId is :"+org.getOrganizationId());
//                            long attributeId=attribute.getAttributeId();
//                            LOGGER.debug("attributeId :"+attributeId);
//                            // if(Validator.isNotNull(attribute)) {
//                            //processs to delete the record of Attribute_Value record based on taking attributeId
//                            try {
//                                DynamicQuery dynamicQueryAttributeValue=Attribute_ValueLocalServiceUtil.dynamicQuery();
//                                //take attributeId to fetch the Attribute_Value record
//                                dynamicQueryAttributeValue.add(PropertyFactoryUtil.forName("attributeId").eq(attributeId));
//                                List<Attribute_Value> listOfAttributeValue=Attribute_ValueLocalServiceUtil.dynamicQuery(dynamicQueryAttributeValue);
//                                LOGGER.info("listOfAttributeValue :"+listOfAttributeValue);
//                                if(Validator.isNotNull(listOfAttribute)) {
//                                    for(Attribute_Value attribute_Value :listOfAttributeValue) {
//                                        attribute_Value=Attribute_ValueLocalServiceUtil.deleteAttribute_Value(attribute_Value);
//                                        LOGGER.info("removed vy_attribute_Value are :"+attribute_Value);
//                                    }
//
//                                }
//                            }catch (Exception e) {
//                                LOGGER.error("exception raised while deleting Attribute_Value is"+e.getMessage());
//                            }
//
//                            try {
//                                //processs to delete the record of Attribute_Entity record based on taking attributeId
//                                DynamicQuery dynamicQueryAttributeEntity=Attribute_EntityLocalServiceUtil.dynamicQuery();
//                                //take attributeId to fetch the record of Attribute_Entity record
//                                dynamicQueryAttributeEntity.add(PropertyFactoryUtil.forName("attributeId").eq(attributeId));
//                                List<Attribute_Entity> listOfAttributeEntity=Attribute_EntityLocalServiceUtil.dynamicQuery(dynamicQueryAttributeEntity);
//                                LOGGER.info("listOfAttributeEntity :"+listOfAttributeEntity);
//                                if(Validator.isNotNull(listOfAttributeEntity)) {
//                                    for(Attribute_Entity attribute_Entity:listOfAttributeEntity) {
//                                        attribute_Entity=Attribute_EntityLocalServiceUtil.deleteAttribute_Entity(attribute_Entity);
//                                        LOGGER.info("removed vy_attribute_Entity are :"+attribute_Entity);
//                                    }
//                                }
//                            }catch (Exception e) {
//                                LOGGER.error("exception raised while deleting Attribute_Entity is"+e.getMessage());
//                            }
//
//                            //process to delete the record of Attribute_Role  record based on taking attributeId
//                            try {
//                                DynamicQuery dynamicQueryAttributeRole=Attribute_RoleLocalServiceUtil.dynamicQuery();
//                                //take attributeId to fetch the record of Attribute_Role record
//                                dynamicQueryAttributeRole.add(PropertyFactoryUtil.forName("attributeId").eq(attributeId));
//                                List<Attribute_Role> listOfAttributeRole=Attribute_RoleLocalServiceUtil.dynamicQuery(dynamicQueryAttributeRole);
//                                LOGGER.info("listOfAttributeRole :"+listOfAttributeRole);
//                                if(Validator.isNotNull(listOfAttributeRole)) {
//                                    for(Attribute_Role attribute_Role:listOfAttributeRole) {
//                                        attribute_Role=Attribute_RoleLocalServiceUtil.deleteAttribute_Role(attribute_Role) ;
//                                        LOGGER.info("removed attribute_Role  are :"+attribute_Role);
//                                    }
//                                }
//                            }catch (Exception e) {
//                                LOGGER.error("exception raised while deleting Attribute_Role is"+e.getMessage());
//                            }
//                            //process to delete the record of attriValueEntitymapping
//                            try {
//                                //pass attributeId at the artifactId , get list Of AttriValueEntityMapping
//                                List<AttriValue_Entity_Mapping> listOfAttriValueEntityMapping=AttriValue_Entity_MappingLocalServiceUtil.findByArtifactId(attributeId);
//                                LOGGER.info("listOfAttriValueEntityMapping :"+listOfAttriValueEntityMapping);
//                                if(Validator.isNotNull(listOfAttriValueEntityMapping)) {
//                                    for(AttriValue_Entity_Mapping attriValue_Entity_Mapping:listOfAttriValueEntityMapping ) {
//                                        attriValue_Entity_Mapping=AttriValue_Entity_MappingLocalServiceUtil.deleteAttriValue_Entity_Mapping(attriValue_Entity_Mapping) ;
//                                        LOGGER.info("removed AttriValue_Entity_Mapping are :"+attriValue_Entity_Mapping);
//                                    }
//                                }
//
//                            } catch (NoSuchAttriValue_Entity_MappingException e) {
//                                LOGGER.error("exception occured while deleting attriValue_Entity_Mapping");
//                            } catch (SystemException e) {
//                                LOGGER.error("exception occured while deleting attriValue_Entity_Mapping");
//                            }
//
//
//                            //process to delete the record of Attribute_User
//                            try {
//                                List<Attribute_User> listOfAttributeUser= Attribute_UserLocalServiceUtil.findByArtifactId(attributeId);
//                                LOGGER.info("listOfAttributeUser :"+listOfAttributeUser);
//                                if(Validator.isNotNull(listOfAttributeUser)) {
//                                    for(Attribute_User attribute_User : listOfAttributeUser ) {
//
//                                        attribute_User=Attribute_UserLocalServiceUtil.deleteAttribute_User(attribute_User);
//                                        LOGGER.info("removed attribute_User  are :"+attribute_User);
//                                    }
//
//
//                                }
//                            }catch (Exception e) {
//                                LOGGER.error("exception raised while deleting Attribute_Role is"+e.getMessage());
//                            }
//                        }
//
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting Attribute is :"+e.getMessage());
//                }
//
//            }//run method
//        };//thread2
//
//        thread2.start();
//        LOGGER.info("courses table --");
//        try {
//            DynamicQuery dynamicQueryCourses1=CoursesLocalServiceUtil.dynamicQuery();
//            dynamicQueryCourses1.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//            List<Courses> listOfCourses1=CoursesLocalServiceUtil.dynamicQuery(dynamicQueryCourses1);
//            LOGGER.info("listOfCourses1 :"+listOfCourses1);
//            if(Validator.isNotNull(listOfCourses1)) {
//                for(Courses courses1 : listOfCourses1) {
//                    courses1=CoursesLocalServiceUtil.deleteCourses(courses1);
//                    LOGGER.info("removed courses table is :"+courses1+"                      OrganizationId "+org.getOrganizationId());
//                }
//            }
//        }catch (Exception e) {
//            LOGGER.error("exception raised while deleting Courses is :"+e.getMessage());
//        }
//
//        Thread thread3=new Thread() {
//            public synchronized void run() {
//
//                try {
//                    DynamicQuery dynamicQueryEntityFunctionality=Entity_Functionality_Artifact_Rule_OrgLocalServiceUtil.dynamicQuery();
//                    dynamicQueryEntityFunctionality.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<Entity_Functionality_Artifact_Rule_Org> listOfEntityFunctionality=Entity_Functionality_Artifact_Rule_OrgLocalServiceUtil.dynamicQuery(dynamicQueryEntityFunctionality);
//                    LOGGER.info("listOfEntityFunctionality :"+listOfEntityFunctionality);
//                    if(Validator.isNotNull(listOfEntityFunctionality)) {
//                        for(Entity_Functionality_Artifact_Rule_Org entity_Functionality_Artifact_Rule_Org:listOfEntityFunctionality) {
//                            Entity_Functionality_Artifact_Rule_OrgLocalServiceUtil.deleteEntity_Functionality_Artifact_Rule_Org(entity_Functionality_Artifact_Rule_Org);
//                            LOGGER.info("Entity_Functionality_Artifact_Rule_Org table removed , OrganizationId "+org.getOrganizationId());
//                        }
//                    }
//                }catch (Exception e) {
//                    LOGGER.error("exception raised while deleting Entity_Functionality_Artifact_Rule_Org is :"+e.getMessage());
//                }
//                LOGGER.info("removing the packages service related mapping if any");
//                long packagOrgartifactTypeId=0;
//
//                try {
//                    packagOrgartifactTypeId=ApplicationParamValueLocalServiceUtil.getApplicationParamValueId("Package Artifact Type", "organizationId");
//                    List<com.vidyayug.packages.model.Packages>  packagesList=  PackagesLocalServiceUtil.findByartifactTypeIdAndArtifactTypeValue(packagOrgartifactTypeId, org.getOrganizationId());
//                    LOGGER.info("packagesList :"+packagesList);
//                    for(com.vidyayug.packages.model.Packages packages:packagesList)
//                    {
//                        LOGGER.info("before delete org related package finding package dependant mapping PackageRules ");
//                        try {
//                            List<PackageRules>  packageRules  =	PackageRulesLocalServiceUtil.findByPackageId(packages.getPackageId());
//                            if(Validator.isNotNull(packageRules))
//                            {	for(PackageRules  packageRule :packageRules)
//                            {
//                                PackageRulesLocalServiceUtil.deletePackageRules(packageRule );
//                                LOGGER.info("removed PackageRules PackageRulesid="+packageRule.getPRId()+" mapping, OrganizationId "+org.getOrganizationId());
//                            }
//                            }
//                        } catch (NoSuchPackageRulesException e) {
//                            LOGGER.error("exception raised while deleting packageRules :"+e.getMessage());
//                        }
//
//                        LOGGER.info("before delete org related package finding package dependant mapping PackageRulesHistory");
//                        try {
//                            DynamicQuery packageRulesHistoryDQ= PackageRulesHistoryLocalServiceUtil.dynamicQuery();
//                            packageRulesHistoryDQ.add(PropertyFactoryUtil.forName("packageId").eq(packages.getPackageId()));
//                            List<PackageRulesHistory> packageRulesHistories= PackageRulesHistoryLocalServiceUtil.dynamicQuery(packageRulesHistoryDQ);
//                            LOGGER.info("packageRulesHistories :"+packageRulesHistories);
//                            if(Validator.isNotNull(packageRulesHistories))
//                            {
//                                for(PackageRulesHistory packageRulesHistory:packageRulesHistories )
//                                {
//                                    PackageRulesHistoryLocalServiceUtil.deletePackageRulesHistory(packageRulesHistory);
//                                    LOGGER.info("removed PackageRulesHistory PackageRulesHistoryId="+packageRulesHistory.getPRHId()+" mapping  removed, OrganizationId "+org.getOrganizationId());
//                                }
//                            }
//                        }catch(Exception e) {
//                            LOGGER.error("exception raised while deleting PackageRulesHistory is :"+e.getMessage());
//                        }
//
//                        LOGGER.info("before delete org related package finding package dependant mapping PackageRulesHistory");
//                        try {
//                            DynamicQuery course_PackagedynamicQuery =Course_PackageLocalServiceUtil.dynamicQuery();
//                            course_PackagedynamicQuery.add(PropertyFactoryUtil.forName("packageId").eq(packages.getPackageId()));
//                            List<Course_Package> course_PackageList= Course_PackageLocalServiceUtil.dynamicQuery(course_PackagedynamicQuery);
//                            LOGGER.info("course_PackageList :"+course_PackageList);
//                            if(Validator.isNotNull(course_PackageList))
//                            {
//                                for(Course_Package course_Package:course_PackageList )
//                                {
//                                    Course_PackageLocalServiceUtil.deleteCourse_Package(course_Package);
//                                    LOGGER.info("removed Course_Package Course_Packageid="+course_Package.getCourseId()+" mapping , OrganizationId "+org.getOrganizationId());
//                                }
//                            }
//                        }catch(Exception e) {
//                            LOGGER.error("exception raised while deleting PackageRulesHistory is :"+e.getMessage());
//                        }
//
//                        LOGGER.info("before delete org related package finding package dependant mapping PackageRulesHistory");
//                        try {
//                            DynamicQuery course_PackageHistorydynamicQuery =Course_PackageHistoryLocalServiceUtil.dynamicQuery();
//                            course_PackageHistorydynamicQuery.add(PropertyFactoryUtil.forName("packageId").eq(packages.getPackageId()));
//                            List<Course_PackageHistory> Course_PackageHistoryList= Course_PackageLocalServiceUtil.dynamicQuery(course_PackageHistorydynamicQuery);
//                            LOGGER.info("Course_PackageHistoryList :"+Course_PackageHistoryList);
//                            if(Validator.isNotNull(Course_PackageHistoryList))
//                            {
//                                for(Course_PackageHistory course_History_Package:Course_PackageHistoryList )
//                                {
//                                    Course_PackageHistoryLocalServiceUtil.deleteCourse_PackageHistory(course_History_Package);
//                                    LOGGER.info("removed Course_PackageHistory Course_PackageHistoryId="+course_History_Package.getCoursePackageHistroyId()+" mapping , OrganizationId "+org.getOrganizationId());
//                                }
//                            }
//                        }catch(Exception e) {
//                            LOGGER.error("exception raised while deleting Course_PackageHistory is :"+e.getMessage());
//                        }
//
//
//                        LOGGER.info("before delete org related package finding package dependant mapping Package_ArtifactMapping");
//
//                        try {
//                            List<Package_ArtifactMapping>  packageArtifactMappingList= Package_ArtifactMappingLocalServiceUtil.findByPackageId(packages.getPackageId());
//                            LOGGER.info("packageArtifactMappingList :"+packageArtifactMappingList);
//                            if(Validator.isNotNull(packageArtifactMappingList))
//                            {
//                                for(Package_ArtifactMapping package_ArtifactMapping: packageArtifactMappingList )
//                                {
//                                    Package_ArtifactMappingLocalServiceUtil.deletePackage_ArtifactMapping(package_ArtifactMapping);
//                                    LOGGER.info("Package_ArtifactMapping Package_ArtifactMappingId="+package_ArtifactMapping.getPkAMId()+" mapping , OrganizationId "+org.getOrganizationId());
//                                }
//                            }
//                        }catch(Exception e) {
//                            LOGGER.error("exception raised while deleting Package_ArtifactMapping is :"+e.getMessage());
//                        }
//
//
//                    }
//
//                } catch (NoSuchPackagesException e) {
//                    LOGGER.error("exception raised NoSuchPackagesException :"+e.getMessage());
//                } catch (SystemException e) {
//                    LOGGER.error("exception occured :"+e.getMessage());
//                }
//
//                LOGGER.info("finding  package history related mapping if any ");
//                try {
//                    DynamicQuery  packDynamicQuery  =PackagesHistoryLocalServiceUtil.dynamicQuery();
//                    packDynamicQuery.add(PropertyFactoryUtil.forName("artifactTypeId").eq(packagOrgartifactTypeId));
//                    packDynamicQuery.add(PropertyFactoryUtil.forName("artifactTypeValue").eq(org.getOrganizationId()));
//                    List<PackagesHistory>  packagesHistories= PackagesHistoryLocalServiceUtil.dynamicQuery(packDynamicQuery);
//                    LOGGER.info("packagesHistories :"+packagesHistories);
//                    if(Validator.isNotNull(packagesHistories))
//                    {
//                        for(PackagesHistory  packagesHistory:packagesHistories)
//                        {
//                            PackagesHistoryLocalServiceUtil.deletePackagesHistory(packagesHistory);
//                            LOGGER.info("removed PackagesHistory PackagesHistoryId="+packagesHistory.getPHId()+" mapping , OrganizationId "+org.getOrganizationId());
//                        }
//                    }
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting PackagesHistory is :"+e.getMessage());
//                }
//
//                LOGGER.info("stripePaymentgateway --");
//                try {
//                    DynamicQuery dynamicQueryStripePlan= StripePlanDetailsLocalServiceUtil.dynamicQuery();
//                    dynamicQueryStripePlan.add(PropertyFactoryUtil.forName("organizationId").eq(org.getOrganizationId()));
//                    List<StripePlanDetails> listOfStripePlainDetails= StripePlanDetailsLocalServiceUtil.dynamicQuery(dynamicQueryStripePlan);
//                    LOGGER.info("listOfStripePlainDetails :"+listOfStripePlainDetails);
//                    if(Validator.isNotNull(listOfStripePlainDetails)) {
//                        for(StripePlanDetails stripePlanDetails:listOfStripePlainDetails) {
//                            stripePlanDetails=StripePlanDetailsLocalServiceUtil.deleteStripePlanDetails(stripePlanDetails);
//                            LOGGER.info("StripePlainDetails removed , OrganizationId "+org.getOrganizationId());
//                            long stripePlanDetailsId=stripePlanDetails.getStripe_planDetails_id();
//                            LOGGER.info("stripePlanDetailsId :"+stripePlanDetailsId);
//
//                            //process to delete the record from StripeCustomerPlanMapping
//                            DynamicQuery dynamicQueryStripeCustomerPlanMapping=StripeCustomerPlanMappingLocalServiceUtil.dynamicQuery();
//                            dynamicQueryStripeCustomerPlanMapping.add(PropertyFactoryUtil.forName("Stripe_planDetails_id").eq(stripePlanDetailsId));
//                            List<StripeCustomerPlanMapping> listOfStripeCustomerPlanMapping=StripeCustomerPlanMappingLocalServiceUtil.dynamicQuery(dynamicQueryStripeCustomerPlanMapping);
//                            LOGGER.info("listOfStripeCustomerPlanMapping :"+listOfStripeCustomerPlanMapping);
//                            if(Validator.isNotNull(listOfStripeCustomerPlanMapping)) {
//
//                                for(StripeCustomerPlanMapping stripeCustomerPlanMapping:listOfStripeCustomerPlanMapping) {
//                                    stripeCustomerPlanMapping=StripeCustomerPlanMappingLocalServiceUtil.deleteStripeCustomerPlanMapping(stripeCustomerPlanMapping);
//                                    LOGGER.info("removed record of stripeCustomerPlanMapping is :"+stripeCustomerPlanMapping);
//                                    String Stripe_customer_id= stripeCustomerPlanMapping.getStripe_customer_id();
//                                    long Stripe_customer_plan_mapping_id= stripeCustomerPlanMapping.getStripe_customer_plan_mapping_id();
//                                    LOGGER.info("Stripe_customer_id :"+Stripe_customer_id+"         Stripe_customer_plan_mapping_id:"+Stripe_customer_plan_mapping_id);
//
//                                    try {
//                                        User_StripeCustomerMapping user_StripeCustomerMapping=User_StripeCustomerMappingLocalServiceUtil.findByStripeCustomerId(Stripe_customer_id);
//                                        LOGGER.info("user_StripeCustomerMapping :"+user_StripeCustomerMapping);
//                                        if(Validator.isNotNull(user_StripeCustomerMapping)) {
//                                            user_StripeCustomerMapping=User_StripeCustomerMappingLocalServiceUtil.deleteUser_StripeCustomerMapping(user_StripeCustomerMapping);
//                                            LOGGER.info("removed user_StripeCustomerMapping is :"+user_StripeCustomerMapping);
//                                        }
//                                    } catch (NoSuchUser_StripeCustomerMappingException e) {
//                                        LOGGER.error("exception occured while removing user_StripeCustomerMapping");
//                                    } catch (SystemException e) {
//                                        LOGGER.error("exception occured while removing user_StripeCustomerMapping");
//                                    }
//
//
//                                    try {
//                                        List<StripeCustomerSubscriptionMapping> listOfstripeCustomerSubscriptionMapping=StripeCustomerSubscriptionMappingLocalServiceUtil.findByCustPlanMappingId(Stripe_customer_plan_mapping_id);
//                                        LOGGER.info("listOfstripeCustomerSubscriptionMapping :"+listOfstripeCustomerSubscriptionMapping);
//                                        if(Validator.isNotNull(listOfstripeCustomerSubscriptionMapping)) {
//                                            for(StripeCustomerSubscriptionMapping stripeCustomerSubscriptionMapping:listOfstripeCustomerSubscriptionMapping) {
//                                                stripeCustomerSubscriptionMapping= StripeCustomerSubscriptionMappingLocalServiceUtil.deleteStripeCustomerSubscriptionMapping(stripeCustomerSubscriptionMapping);
//                                                LOGGER.info("removed stripeCustomerSubscriptionMapping is :"+stripeCustomerSubscriptionMapping);
//                                            }
//                                        }
//                                    } catch (NoSuchStripeCustomerSubscriptionMappingException e) {
//                                        LOGGER.error("exception raised is :"+e.getMessage());
//                                    }
//
//
//                                    try {
//                                        StripeCustomerCardMapping stripeCustomerCardMapping=StripeCustomerCardMappingLocalServiceUtil.findByStripeCustomerId(Stripe_customer_id);
//                                        if(Validator.isNotNull(stripeCustomerCardMapping)) {
//                                            stripeCustomerCardMapping=StripeCustomerCardMappingLocalServiceUtil.deleteStripeCustomerCardMapping(stripeCustomerCardMapping);
//                                            LOGGER.info("removed stripeCustomerCardMapping is "+stripeCustomerCardMapping);
//                                            String Stripe_card_id=  stripeCustomerCardMapping.getStripe_card_id();
//
//                                            if(Validator.isNotNull(Stripe_card_id)) {
//                                                try {
//                                                    StripeCardDetails stripeCardDetails=StripeCardDetailsLocalServiceUtil.findByStripeCardId(Stripe_card_id);
//                                                    if(Validator.isNotNull(stripeCardDetails)) {
//                                                        stripeCardDetails=StripeCardDetailsLocalServiceUtil.deleteStripeCardDetails(stripeCardDetails);
//                                                        LOGGER.info("removed stripeCardDetails is :"+stripeCardDetails);
//                                                    }
//                                                } catch (NoSuchStripeCardDetailsException e) {
//                                                    LOGGER.error("exception occured while removing StripeCardDetails");
//                                                }
//
//                                            }
//                                        }
//                                    } catch (NoSuchStripeCustomerCardMappingException e) {
//                                        LOGGER.error("exception occured while removing StripeCustomerCardMapping");
//                                    } catch (SystemException e) {
//                                        LOGGER.error("exception occured while removing StripeCustomerCardMapping");
//                                    }
//
//
//                                    try {
//                                        DynamicQuery dynamicQueryProviderDetails=ProviderDetailsLocalServiceUtil.dynamicQuery();
//                                        dynamicQueryProviderDetails.add(PropertyFactoryUtil.forName("organizationId").eq(org.getOrganizationId()));
//                                        List<ProviderDetails> listOfProviderDetails=ProviderDetailsLocalServiceUtil.dynamicQuery(dynamicQueryProviderDetails);
//                                        LOGGER.info("listOfProviderDetails :"+listOfProviderDetails);
//                                        if(Validator.isNotNull(listOfProviderDetails)) {
//                                            for(ProviderDetails providerDetails:listOfProviderDetails) {
//                                                providerDetails= ProviderDetailsLocalServiceUtil.deleteProviderDetails(providerDetails);
//                                                LOGGER.info("removed providerDetails is "+providerDetails);
//                                            }
//                                        }
//                                    }catch(Exception e) {
//                                        LOGGER.error("exception raised while deleting ProviderDetails is "+e.getMessage());
//                                    }
//
//
//                                    try {
//                                        DynamicQuery dynamicQueryVyPlainDetailsAM=VYPlanDetailsArtifactMappingLocalServiceUtil.dynamicQuery();
//                                        dynamicQueryVyPlainDetailsAM.add(PropertyFactoryUtil.forName("planDetailsId").eq(stripePlanDetailsId));
//                                        List<VYPlanDetailsArtifactMapping> listOfVYPlanDetailsArtifactMapping=VYPlanDetailsArtifactMappingLocalServiceUtil.dynamicQuery(dynamicQueryVyPlainDetailsAM);
//                                        LOGGER.info("listOfVYPlanDetailsArtifactMapping :"+listOfVYPlanDetailsArtifactMapping);
//                                        if(Validator.isNotNull(listOfVYPlanDetailsArtifactMapping)) {
//                                            for(VYPlanDetailsArtifactMapping vyPlanDetailsArtifactMapping:listOfVYPlanDetailsArtifactMapping) {
//                                                vyPlanDetailsArtifactMapping=VYPlanDetailsArtifactMappingLocalServiceUtil.deleteVYPlanDetailsArtifactMapping(vyPlanDetailsArtifactMapping);
//                                                LOGGER.info("removed vyPlanDetailsArtifactMapping is "+vyPlanDetailsArtifactMapping);
//                                            }
//                                        }
//                                    }catch(Exception e) {
//                                        LOGGER.error("exception raised while deleting VYPlanDetailsArtifactMapping is "+e.getMessage());
//                                    }
//
//                                }
//
//                            }
//                        }
//
//                    }
//                    LOGGER.info("stripePaymentgateway end--");
//                }catch(Exception e) {
//                    LOGGER.error("exception raised while deleting stripePaymentgateway and its mapping table is "+e.getMessage());
//                }
//
//                try {
//                    DynamicQuery dynamicQueryOrgArtifactSite=Org_Artifact_SiteLocalServiceUtil.dynamicQuery();
//                    dynamicQueryOrgArtifactSite.add(PropertyFactoryUtil.forName("orgId").eq(org.getOrganizationId()));
//                    List<Org_Artifact_Site> listOrgArtifactSite=Org_Artifact_SiteLocalServiceUtil.dynamicQuery(dynamicQueryOrgArtifactSite);
//                    LOGGER.info("listOrgArtifactSite is :"+listOrgArtifactSite);
//                    if(Validator.isNotNull(listOrgArtifactSite)) {
//
//                        try {
//                            for(Org_Artifact_Site org_Artifact_Site:listOrgArtifactSite) {
//                                org_Artifact_Site=Org_Artifact_SiteLocalServiceUtil.deleteOrg_Artifact_Site(org_Artifact_Site);
//
//                                LOGGER.info("removed org_Artifact_Site is "+org_Artifact_Site);
//                                //write delete org_Artifact_Site records from sakai db
//                                String siteId=org_Artifact_Site.getSakaiSiteId();
//
//                                //delete course table of sakai db
//                                //call webservices call from here
//                                LOGGER.debug("call to webservices ******");
//
//                                try {
//                                    deleteCourse(siteId);
//                                }catch(Exception e) {
//                                    e.printStackTrace();
//                                    deleteCourse(siteId);
//                                }
//
//                            }//for
//                        }catch(Exception e) {
//                            LOGGER.error("exception raised while deleting Org_Artifact_Site  is :"+e.getMessage());
//                        }
//                    }
//                }catch (Exception e) {
//                    LOGGER.error("exception raised while deleting Org_Artifact_Site  is :"+e.getMessage());
//                }
//
//            }
//        };
//        thread3.setUncaughtExceptionHandler(exceptionHandler);
//        thread3.start();
//
//
//        super.onBeforeRemove(org);
//    }
//
//
//    public void deleteCourse(String siteId) {
//        LOGGER.debug("siteId at deleteCourse is :"+siteId);
//
//        //User record delete from sakai db
//        String jsonData = "";
//        String  login = "login";
//        HttpResponse httpResponse=null;
//        HttpEntity httpEntity=null;
//        String responseString=null;
//        int statusCode=0;
//        String sessionid=null;
//
//        //HttpClient httpClient=HttpClientBuilder.create().build();
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000000).setConnectTimeout(50000000).setConnectionRequestTimeout(50000000).build();
//        CloseableHttpClient httpClient= HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
//
//        LOGGER.debug("login client obj created");
//        String sakaiRestServiceURL =DeploymentConfigLocalServiceUtil.getsakaiRestSserviceConnectorURL();
//        sakaiRestServiceURL=sakaiRestServiceURL.concat("login");
//        LOGGER.debug("sakaiRestServiceURL :"+sakaiRestServiceURL);
//        String loginUrl =  DeploymentConfigLocalServiceUtil.getSakaiWSLoginURL();
//        String adminUsername = DeploymentConfigLocalServiceUtil.getSakaiAdminUserName();
//        String adminPassword =DeploymentConfigLocalServiceUtil.getSakaiAdminPassword();
//
//        HttpPost httpPost=new HttpPost(sakaiRestServiceURL);
//        jsonData = "{\"endPoint\":\"" + loginUrl + "\",\"method\":\"" + login + "\",\"userName\":\"" + adminUsername
//                + "\",\"password\":\"" + adminPassword + "\"}";
//        LOGGER.debug("login jsonData is :"+jsonData);
//        httpPost.setHeader("Content-type", "application/json");
//
//        try {
//            httpPost.setEntity(new StringEntity(jsonData));
//            httpResponse=httpClient.execute(httpPost);
//            LOGGER.debug("response came afer login :"+httpResponse);
//        }catch(SystemException | IOException e) {
//            LOGGER.error("exception raised while connecting to sakai :"+e.getMessage());
//        }
//
//        httpEntity=httpResponse.getEntity();
//        try {
//            responseString = EntityUtils.toString(httpEntity, "UTF-8");
//        }catch(ParseException | IOException e) {
//            LOGGER.error("exception raised while getting respose after login"+e.getMessage());
//        }
//
//        statusCode=httpResponse.getStatusLine().getStatusCode();
//        if(String.valueOf(statusCode).equals("200")  || String.valueOf(statusCode).equals("201")) {
//            LOGGER.debug("login successfully done , now using sessionId delete courses");
//            sessionid=responseString;
//            LOGGER.debug("sessionId getting after login :"+sessionid);
//            //here login to sakai complete, now delete the user from sakai
//            if(Validator.isNotNull(sessionid)) {
//
//                HttpResponse httpResponse2=null;
//                HttpEntity httpEntity2=null;
//                String resString=null;
//
//                //HttpClient httpClient2=HttpClientBuilder.create().build();
//                RequestConfig requestConfig2 = RequestConfig.custom().setSocketTimeout(50000000).setConnectTimeout(50000000).setConnectionRequestTimeout(50000000).build();
//                CloseableHttpClient httpClient2= HttpClientBuilder.create().setDefaultRequestConfig(requestConfig2).build();
//
//
//                String scriptUrl = DeploymentConfigLocalServiceUtil.getSakaiWSScriptURL();
//                String courseDeleteMethod="removeSite";
//
//                sakaiRestServiceURL=DeploymentConfigLocalServiceUtil.getsakaiRestSserviceConnectorURL().concat(courseDeleteMethod);
//
//                LOGGER.info("sakaiRestServiceURL :"+sakaiRestServiceURL);
//                HttpPost httpPost2=new HttpPost(sakaiRestServiceURL);
//                String deleteCourseParams= "{\"endPoint\":\"" + scriptUrl + "\",\"method\":\"" + courseDeleteMethod
//                        + "\",\"sessionid\":\"" + sessionid + "\",\"siteId\":\"" + siteId +  "\"}";
//
//
//                httpPost2.setHeader("Content-type", "application/json");
//                try {
//                    httpPost2.setEntity(new StringEntity(deleteCourseParams));
//                    httpResponse2=httpClient2.execute(httpPost2);
//
//                    LOGGER.info("response came after deleting course :"+httpResponse2);
//                }catch(IOException e) {
//                    LOGGER.error("exception raised while excecuting post req."+e.getMessage());
//                }
//
//                int status=httpResponse2.getStatusLine().getStatusCode();
//                if(String.valueOf(status).equals("200") || String.valueOf(status).equals("201")) {
//                    LOGGER.debug("course deleted from sakai_");
//                    try {
//                        httpClient.close();
//                        httpClient2.close();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//
//
//                }
//                else {
//                    LOGGER.warn("something went wrong , User not deleted from sakai_user");
//                }
//            }
//        }
//
//    }
//
//}
