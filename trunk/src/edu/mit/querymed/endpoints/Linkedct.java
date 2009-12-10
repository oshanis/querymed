//package edu.mit.querymed.endpoints;
//
//import com.hp.hpl.jena.query.QueryExecutionFactory;
//import com.hp.hpl.jena.query.QuerySolution;
//import com.hp.hpl.jena.query.ResultSet;
//
//import edu.mit.querymed.services.Util;
//
//	public static String[] properties  = {
//		"http://data.linkedct.org/resource/linkedct/study_design",
//		"http://data.linkedct.org/resource/linkedct/eligibility_healthy_volunteers",
//		"http://data.linkedct.org/resource/linkedct/overall_contact_phone",
//		"http://data.linkedct.org/resource/linkedct/has_dmc",
//		"http://data.linkedct.org/resource/linkedct/overall_official",
//		"http://data.linkedct.org/resource/linkedct/acronym",
//		"http://data.linkedct.org/resource/linkedct/phase",
//		"http://data.linkedct.org/resource/linkedct/lastchanged_date",
//		"http://data.linkedct.org/resource/linkedct/number_of_groups",
//		"http://data.linkedct.org/resource/linkedct/overall_contact_last_name",
//		"http://data.linkedct.org/resource/linkedct/eligibility_maximum_age",
//		"http://data.linkedct.org/resource/linkedct/id",
//		"http://data.linkedct.org/resource/linkedct/location",
//		"http://data.linkedct.org/resource/linkedct/biospec_retention",
//		"http://data.linkedct.org/resource/linkedct/study_type",
//		"http://data.linkedct.org/resource/linkedct/primary_completion_date",
//		"http://data.linkedct.org/resource/linkedct/primary_outcomes",
//		"http://data.linkedct.org/resource/linkedct/secondary_id",
//		"http://data.linkedct.org/resource/linkedct/summary",
//		"http://data.linkedct.org/resource/linkedct/condition",
//		"http://data.linkedct.org/resource/linkedct/nct_id",
//		"http://xmlns.com/foaf/0.1/page",
//		"http://data.linkedct.org/resource/linkedct/org_study_id",
//		"http://data.linkedct.org/resource/linkedct/oversight",
//		"http://data.linkedct.org/resource/linkedct/download_date",
//		"http://data.linkedct.org/resource/linkedct/reference",
//		"http://www.w3.org/2000/01/rdf-schema#label",
//		"http://data.linkedct.org/resource/linkedct/overall_status",
//		"http://data.linkedct.org/resource/linkedct/eligibility_sampling_method",
//		"http://data.linkedct.org/resource/linkedct/results_reference",
//		"http://data.linkedct.org/resource/linkedct/official_title",
//		"http://data.linkedct.org/resource/linkedct/brief_title",
//		"http://data.linkedct.org/resource/linkedct/overall_contact_phone_ext",
//		"http://data.linkedct.org/resource/linkedct/number_of_arms",
//		"http://data.linkedct.org/resource/linkedct/link",
//		"http://data.linkedct.org/resource/linkedct/overall_contact_email",
//		"http://data.linkedct.org/resource/linkedct/nct_alias",
//		"http://data.linkedct.org/resource/linkedct/firstreceived_date",
//		"http://data.linkedct.org/resource/linkedct/why_stopped",
//		"http://data.linkedct.org/resource/linkedct/eligibility_minimum_age",
//		"http://data.linkedct.org/resource/linkedct/enrollment",
//		"http://data.linkedct.org/resource/linkedct/eligibility_study_pop",
//		"http://data.linkedct.org/resource/linkedct/source",
//		"http://data.linkedct.org/resource/linkedct/end_date",
//		"http://data.linkedct.org/resource/linkedct/criteria",
//		"http://data.linkedct.org/resource/linkedct/arm_group",
//		"http://data.linkedct.org/resource/linkedct/start_date",
//		"http://data.linkedct.org/resource/linkedct/eligibility_gender",
//		"http://data.linkedct.org/resource/linkedct/description",
//		"http://data.linkedct.org/resource/linkedct/secondary_outcomes",
//		"http://data.linkedct.org/resource/linkedct/intervention",
//		"http://data.linkedct.org/resource/linkedct/collaborator_agency",
//		"http://data.linkedct.org/resource/linkedct/lead_sponsor_agency",
//		"http://data.linkedct.org/resource/linkedct/biospec",
//		"http://data.linkedct.org/resource/linkedct/verification_date",
//		"http://www.w3.org/1999/02/22-rdf-syntax-ns#type",
//		"http://data.linkedct.org/resource/linkedct/agency_id",
//		"http://data.linkedct.org/resource/linkedct/agency_name",
//		"http://data.linkedct.org/resource/linkedct/citation",
//		"http://www.w3.org/2002/07/owl#sameAs",
//		"http://data.linkedct.org/resource/linkedct/results_reference_id",
//		"http://data.linkedct.org/resource/linkedct/PMid",
//		"http://data.linkedct.org/resource/linkedct/safety_issue",
//		"http://data.linkedct.org/resource/linkedct/measure",
//		"http://data.linkedct.org/resource/linkedct/time_frame",
//		"http://data.linkedct.org/resource/linkedct/primary_outcomes_id",
//		"http://data.linkedct.org/resource/linkedct/secondary_outcomes_id",
//		"http://data.linkedct.org/resource/linkedct/condition_id",
//		"http://data.linkedct.org/resource/linkedct/condition_name",
//		"http://www.w3.org/2000/01/rdf-schema#seeAlso",
//		"http://data.linkedct.org/resource/linkedct/link_id",
//		"http://data.linkedct.org/resource/linkedct/url",
//		"http://data.linkedct.org/resource/linkedct/facility_address_city",
//		"http://data.linkedct.org/resource/linkedct/facility_address_country",
//		"http://data.linkedct.org/resource/linkedct/location_id",
//		"http://xmlns.com/foaf/0.1/based_near",
//		"http://data.linkedct.org/resource/linkedct/facility_address_zip",
//		"http://data.linkedct.org/resource/linkedct/facility_address_state",
//		"http://data.linkedct.org/resource/linkedct/facility_name",
//		"http://data.linkedct.org/resource/linkedct/reference_id",
//		"http://data.linkedct.org/resource/linkedct/oversight_oversight_info_authority",
//		"http://data.linkedct.org/resource/linkedct/oversight_id",
//		"http://data.linkedct.org/resource/linkedct/intervention_name",
//		"http://data.linkedct.org/resource/linkedct/intervention_type",
//		"http://data.linkedct.org/resource/linkedct/intervention_id",
//		"http://data.linkedct.org/resource/linkedct/affiliation",
//		"http://data.linkedct.org/resource/linkedct/last_name",
//		"http://data.linkedct.org/resource/linkedct/overall_official_id", 
//		"http://data.linkedct.org/resource/linkedct/collaborator_agency_name",
//		"http://data.linkedct.org/resource/linkedct/collaborator_agency_id",
//		"http://data.linkedct.org/resource/linkedct/arm_group_label",
//		"http://data.linkedct.org/resource/linkedct/arm_group_type",
//		"http://data.linkedct.org/resource/linkedct/arm_group_id"}
//
//
//public class Linkedct {
//
//	public static void main(String[] args){
//		
//	     String query = Util.prefixes + " SELECT DISTINCT ?p WHERE {?s ?p ?o} ";
//	     ResultSet results = QueryExecutionFactory.sparqlService(Util.LINKEDCT_ENDPOINT,query).execSelect();
//	     while (results.hasNext()){
//	 			QuerySolution solution = results.next();
//	 			String s = solution.get("p").toString();
//	 			System.out.println(s);
//	 		}
//
//	}
//
//}
