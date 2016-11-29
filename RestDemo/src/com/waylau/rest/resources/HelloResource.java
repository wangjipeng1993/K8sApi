package com.waylau.rest.resources;

/**
 * Created by root on 16-11-22.
 */
import com.wjp.k8s.client.impl.K8sRestfulClient;
import com.wjp.k8s.client.tool.Parse;



import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.fabric8.kubernetes.api.model.*;
import net.sf.json.*;
import net.sf.json.JSONException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("/namespaces")
public class HelloResource {
    K8sRestfulClient k8s = new K8sRestfulClient();
    Parse parse =new Parse();



    @GET
    @Path("/name/{i}")
    @Produces(MediaType.TEXT_XML)
    public String userName(@PathParam("i") String i) {

        String name = i;
        return "<User>" + "<Name>" + name + "</Name>" + "</User>";
    }


    //  create Service
    @POST
    @Path("/{namespace}/service")
    @Produces("text/plain;charset=UTF-8")
    public String createService(String entity, @PathParam("namespace") String namespace, @Context HttpServletRequest request) {
        Map ServiceMap = parse.ParseService(entity);
        return k8s.createService((String)ServiceMap.get("metadata_name"), namespace, (Map<String, String>)ServiceMap.get("metadata_labels"),
                (Map<String, String>)ServiceMap.get("spec_selector"), (String)ServiceMap.get("spec_type"), (String)ServiceMap.get("spec_clusterIP"),
                (List<ServicePort>)ServiceMap.get("spec_ports"), (String)ServiceMap.get("spec_status_loadBalancer_ingress_ip"),
                (String)ServiceMap.get("spec_status_loadBalancer_ingress_hostname"));
//        return "create service!!!!!!!!!!!!!!!!!";
    }


    // Create RC
    @POST
    @Path("/{namespace}/replicationtrollers")
    @Produces("text/plain")
    public String createRC(String entity, @PathParam("namespace") String namespace, @Context HttpServletRequest request) {
        Map RcMap = parse.ParseRC(entity);
        return k8s.createRC((String)RcMap.get("metadata_name"), namespace, (Map<String, String>)RcMap.get("metadata_labels"), (String)RcMap.get("spec_replicas"),
                (Map<String, String>)RcMap.get("spec_selector"), (Map<String, String>)RcMap.get("spec_temp_meta_labels"),
                (String)RcMap.get("spec_temp_spec_con_name"), (String)RcMap.get("spec_temp_spec_con_image"), (String)RcMap.get("spec_temp_spec_con_imagepull"),
                (String)RcMap.get("spec_temp_spec_con_workingDir"), (List<String>)RcMap.get("spec_temp_spec_con_command"),
                (List<VolumeMount>)RcMap.get("spec_temp_spec_con_volumeMounts"), (List<ContainerPort>)RcMap.get("spec_temp_spec_con_ports"),
                (List<EnvVar>)RcMap.get("spec_temp_spec_con_env"), (List<Volume>)RcMap.get("spec_temp_spec_volumes"), (String)RcMap.get("spec_temp_spec_restartPolicy"),
                (String)RcMap.get("spec_temp_spec_dnsPolicy"));
//        return "----------> " + entity;
    }


    // Create pod
    @POST
    @Path("/{namespace}/pods")
    @Produces("text/plain")
    public String createPod(String entity, @PathParam("namespace") String namespace, @Context HttpServletRequest request){
        Map PodMap = parse.ParsePod(entity);
        return  k8s.createPod((String)PodMap.get("metadata_name"), namespace,(Map<String, String>)PodMap.get("metadata_labels"),
                (String)PodMap.get("spec_con_name"), (String)PodMap.get("spec_con_images"), (String)PodMap.get("spec_con_imagePullPolicy"),
                (List<String>)PodMap.get("spec_con_command"), (String)PodMap.get("spec_con_workingDir"), (List<VolumeMount>)PodMap.get("spec_con_volumeMounts") ,
                (List<ContainerPort>)PodMap.get("spec_con_ports"), (List<EnvVar>)PodMap.get("spec_con_env"),
                (List<Volume>)PodMap.get("spec_volumes"), (String)PodMap.get("spec_restartpolicy"), (String)PodMap.get("spec_dnsPolicy"));
    }


    // Create namespaces
    @POST
    @Produces("text/plain")
    public String createNamespace(String entity, @Context HttpServletRequest request){
        String metadata_name = parse.ParseNamespace(entity);
        return k8s.createNamespace(metadata_name);
    }


    // get namespace
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return k8s.getNamespace(null);
    }


    // get podlist
    @GET
    @Path("/{namespace}/pods")
    @Produces("text/plain;charset=UTF-8")
    public String getPodList(@PathParam("namespace") String namespace) {
        return k8s.getPod(namespace, null)+"";
    }


    // get pod
    @GET
    @Path("/{namespace}/pods/{name}")
    @Produces("text/plain;charset=UTF-8")
    public String getPod(@PathParam("namespace") String namespace, @PathParam("name") String podName) {
        String NpodName = "name=" + podName + ",";
        return k8s.getPod(namespace, NpodName);
    }


    // get rcList
    @GET
    @Path("/{namespace}/replicationtrollers")
    @Produces("text/plain;charset=UTF-8")
    public String getRcList(@PathParam("namespace") String namespace) {
        return k8s.getRC(namespace, null);
    }


    // get RC
    @GET
    @Path("/{namespace}/replicationtrollers/{Rcname}")
    @Produces("text/plain;charset=UTF-8")
    public String getRc(@PathParam("namespace") String namespace, @PathParam("Rcname") String RcName) {
        String NRcName = "name=" + RcName + ",";
        return k8s.getRC(namespace, NRcName);
    }


    // get ServiceList
    @GET
    @Path("/{namespace}/service")
    @Produces("text/plain;charset=UTF-8")
    public String getServiceList(@PathParam("namespace") String namespace) {
        return k8s.getservice(namespace, null);
    }


    // get Service
    @GET
    @Path("/{namespace}/service/{ServiceName}")
    @Produces("text/plain;charset=UTF-8")
    public String getService(@PathParam("namespace") String namespace, @PathParam("ServiceName") String SerName) {
        String NSerName = "name=" + SerName + ",";
        return k8s.getservice(namespace, NSerName);
    }


    // delete namespace
    @DELETE
    @Path("/{namespace}")
    @Produces("text/plain;charset=UTF-8")
    public String deleteNamespace(@PathParam("namespace") String namespace) {
        return k8s.deleteNamespace(namespace);
    }


    // delete podlist
    @DELETE
    @Path("/{namespace}/pods")
    @Produces("text/plain;charset=UTF-8")
    public String deletePodList(@PathParam("namespace") String namespace) {
        return k8s.deletePod(namespace, null);
    }


    // delete pod
    @DELETE
    @Path("/{namespace}/pods/{name}")
    @Produces("text/plain;charset=UTF-8")
    public String deletePod(@PathParam("namespace") String namespace, @PathParam("name") String podName) {
//        String NpodName = "name=" + podName + ",";
        return k8s.deletePod(namespace, podName);
    }


    // delete rclist
    @DELETE
    @Path("/{namespace}/replicationtrollers")
    @Produces("text/plain;charset=UTF-8")
    public String deleteRcList(@PathParam("namespace") String namespace) {
        return k8s.deleteRc(namespace, null);
    }


    // delete rc
    @DELETE
    @Path("/{namespace}/replicationtrollers/{Rcname}")
    @Produces("text/plain;charset=UTF-8")
    public String deleteRc(@PathParam("namespace") String namespace, @PathParam("Rcname") String RcName) {
//        String NRcName = "name=" + RcName + ",";
        return k8s.deleteRc(namespace, RcName);
    }


    // delete servicelist
    @DELETE
    @Path("/{namespace}/service")
    @Produces("text/plain;charset=UTF-8")
    public String deleteServiceList(@PathParam("namespace") String namespace) {
        return k8s.deleteService(namespace, null);
    }


    // delete service
    @DELETE
    @Path("/{namespace}/service/{ServiceName}")
    @Produces("text/plain;charset=UTF-8")
    public String deleteService(@PathParam("namespace") String namespace, @PathParam("ServiceName") String SerName) {
//        String NSerName = "name=" + SerName + ",";
        return k8s.deleteService(namespace, SerName);
    }



    // scala RC
    @GET
    @Path("/{namespace}/replicationtrollers/{Rcname}/scala/{scalaNum}")
    @Produces("text/plain;charset=UTF-8")
    public String ScalaPod(@PathParam("namespace") String namespace, @PathParam("Rcname") String Rcname,
                           @PathParam("scalaNum") int scalaNum ) {
        return k8s.RcScala(namespace, Rcname, scalaNum);
    }



}