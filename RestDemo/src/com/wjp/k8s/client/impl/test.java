package com.wjp.k8s.client.impl;


import javax.ws.rs.core.MediaType;

//import org.codehaus.jettison.json.JSONArray;
//import org.codehaus.jettison.json.JSONException;
//import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.wjp.k8s.client.tool.Parse;
import io.fabric8.kubernetes.api.model.*;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by root on 16-11-22.
 */
public class test  {
    public static void main(String[] argc) {
        Parse parse = new Parse();

        // Test Post service
        String service = "{\"kind\": \"Service\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"postservice\", \"labels\":" +
                "[{\"abc\":\"abc\", \"abcd\":\"abcd\"}]}, \"spec\": {\"selector\": [{\"abc1\":\"abc\", \"abcd1\":\"abcd\"}]," +
                "\"type\": \"ClusterIP\", \"clusterIP\": \"wangjipeng\", \"ports\":[{\"port\":\"80\", \"targetPort\": \"89\"," +
                "\"protocol\": \"TCP\"}], \"status\":{\"loadBalancer\": {\"ingress\": {\"ip\": \"127.0.0.1\", \"hostname\": \"wjp\"}}} }}";
        JSONObject json = JSONObject.fromObject(service);
        System.out.println("----------------------------------------------------------1");
        Map<String, String> metadata_labels_map ;  // metadata_labels
        Map<String, String> spec_selector_map;  // spec_selector
        String spec_type;  //spec_type
        String spec_clusterIP; // spec_clusterIP
        List<ServicePort> portList;  // spec_ports
        String loadbalance_ingress_ip; // spec_status_loadBalancer_ingress_ip
        String loadbalance_ingress_hostname; // spec_status_loadBalancer_ingress_hostname

        System.out.println("----------------------------------------------------------2");
        String metadata_name = json.getJSONObject("metadata").getString("name");   // metadata_name
        System.out.println("-------------metadata_name----------------------  >" + metadata_name);
        try {
            JSONArray metadata_labels = json.getJSONObject("metadata").getJSONArray("labels");  // metadata_labels
            metadata_labels_map = parse.jsonArrayToMap(metadata_labels);
        }catch (JSONException e){
            metadata_labels_map = new HashMap<String, String>();
        }
        System.out.println("-------------metadata_labels---------------------  > " + metadata_labels_map);
        JSONObject spec = json.getJSONObject("spec");  // spec
        System.out.println("-------------spec -------------------------------  > + " + spec);
        try {
            JSONArray spec_selector = spec.getJSONArray("selector");  // spec_selector
            spec_selector_map = parse.jsonArrayToMap(spec_selector);
        } catch (JSONException e){
            spec_selector_map = new HashMap<String, String>();
        }
        System.out.println("-------------spec_selector-----------------------  > " + spec_selector_map);
        try {
            spec_type = spec.getString("type");  // spec_type
        } catch (JSONException e){
            spec_type = null;
        }
        System.out.println("-------------spec_type--------------------------  > " + spec_type);
        try {
            spec_clusterIP = spec.getString("clusterIP");  //spec_clusterIP
        } catch (JSONException e){
            spec_clusterIP = null;
        }
        System.out.println("-------------spec_cluster---------------------- > " + spec_clusterIP);
        try {
            JSONArray spec_ports = spec.getJSONArray("ports");   // spec_ports
            portList = parse.ServicePortlist(spec_ports);
        } catch (JSONException  e){
            portList = new ArrayList<ServicePort>();
        }
//        for(int o=0; o < spec_ports.size(); o++){
//            JSONObject spec_port_json = JSONObject.fromObject(spec_ports.get(o));
//            System.out.println("---------spec_ports_port--------------- > " + spec_port_json.getString("port"));
//            System.out.println("---------spec_ports_targetPort-------------- > " + spec_port_json.getString("targetPort"));
//            System.out.println("---------spec_ports_protocol----------------- > "  + spec_port_json.getString("protocol"));
//
////            List<ServicePort> portList = new ArrayList<ServicePort>();  // spec.ports
//            ServicePort servicePort = new ServicePort();
////            servicePort.setName("serviceport");
//            servicePort.setPort(Integer.valueOf(spec_port_json.getString("port")));
//            IntOrString intOrString = new IntOrString();
//            intOrString.setIntVal(Integer.valueOf(spec_port_json.getString("targetPort")));
//            servicePort.setTargetPort(intOrString);
//            servicePort.setProtocol(spec_port_json.getString("protocol"));
//            portList.add(servicePort);
//        }
        System.out.println("-------------spec_ports_------------------------  > " + portList);
        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        try {
            loadbalance_ingress_ip = spec.getJSONObject("status").getJSONObject("loadBalancer").getJSONObject("ingress").getString("ip");
        } catch (JSONException e){
            loadbalance_ingress_ip = null;
        }
        System.out.println("-------------spec_status_loadBalancer_ingress_ip----------------- > " + loadbalance_ingress_ip);
        try {
            loadbalance_ingress_hostname = spec.getJSONObject("status").getJSONObject("loadBalancer").getJSONObject("ingress").getString("hostname");
        } catch (JSONException e){
            loadbalance_ingress_hostname = null;
        }
        System.out.println("-------------spec_status_loadBalancer_ingress_hostname------------ > " + loadbalance_ingress_hostname);










        //Test Post RC
//        String rc = "{\"kind\":\"ReplicationController\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"rcnginx\"," +
//                " \"namespace\":\"testdefult\", \"labels\":[{\"abc\":\"abc\", \"iii\":\"ooo\"}]}, \"spec\":{\"replicas\":" +
//                "\"2\", \"selector\":[{\"abc\":\"abc\", \"abcd\":\"abcd\"}], \"template\":{\"metadata\":{\"labels\":[{\"abc\":" +
//                "\"abc\", \"abcd\":\"abcd\"}]}, \"spec\":{\"containers\":{\"name\":\"nginx\", \"image\": \"index.alauda.cn/kiwenla" +
//                "u/nginx:1.9.7\", \"imagePullPolicy\":\"Always\", \"workingDir\":\"/usr/local\", \"command\": [\"echo\", \"echo1\"], \"volumeMounts\":[{\"name\":\"data\"," +
//                "\"mountPath\":\"/usr/local\", \"readOnly\":\"true\"}, {\"name\":\"data1\",\"mountPath\":\"/usr/local1\", \"readOnly\":" +
//                "\"false\"}], \"ports\":[{\"name\":\"portsName\", \"containerPort\":\"80\", \"protocol\":\"TCP\"}, {\"name\":\"portsName1\", \"" +
//                "containerPort\":\"81\", \"protocol\":\"UDP\"}], \"env\":[{\"name\":\"aaa\", \"value\":\"bbb\"}, {\"name\":\"ccc\",\"value\":\"ddd\"" +
//                "}]}, \"volumes\":[{\"name\":\"data\", \"hostPath\":{\"path\": \"/usr/local/docker\"}}, {\"name\":\"data1\", \"hostPath\":{\"path\": \"/" +
//                "usr/local/docker1\"}}], \"restartPolicy\":\"Always\", \"dnsPolicy\":\"Default\" } } } }";
//        System.out.println("ooooooooooooooooooooooooooooooooooooo1");
//        Map<String, String> metadata_labels_map ;  // metadata_labels
//        Map<String, String> spec_selector_map;  // spec_selector
//        Map<String, String> spec_temp_meta_labels_map; // spec_template_metadata_labels
//        String spec_temp_spec_con_imagepull; //spec_temp_spec_con_imagepullpolicy
//        String spec_temp_spec_con_workingDir; //spec_temp_spec_con_workingDir
//        List<String> spec_temp_spec_con_command_list; //spec_temp_spec_con_command
//        List<VolumeMount> spec_temp_spec_con_volumeMount_list;  // spec_temp_spec_con_volumeMounts
//        List<ContainerPort> containerPorts_list; // spec_temp_spec_con_ports
//        List<EnvVar> envVar_list; //spec_temp_spec_con_ env
//        List<Volume> volumes_list;  //spec.temp_spec_volumes
//        String spec_temp_spec_restartPolicy; //spec_temp_spec_restartPolicy
//        String spec_temp_spec_dnsPolicy;  // spec_temp_spec_dnsPolicy
//        System.out.println("ooooooooooooooooooooooooooooooooooooo2");
//        JSONObject json = JSONObject.fromObject(rc);
//        JSONObject metadata = json.getJSONObject("metadata");
//        String metadata_name = metadata.getString("name");  // metadata_name
//        System.out.println("-----------metadata_name------------ > " + metadata_name);
//        try {
//            JSONArray metadata_labels = metadata.getJSONArray("labels");
//            metadata_labels_map = parse.jsonArrayToMap(metadata_labels);   // metadata_labels
//        }catch (JSONException e){
//            metadata_labels_map = new HashMap<String, String>();
//        }
//        System.out.println("-----------metadata_labeles---------- > " + metadata_labels_map);
//        JSONObject spec = json.getJSONObject("spec");  //spec
//        String spec_replicas = spec.getString("replicas");
//        System.out.println("-----------spec_replicas------------ > " + spec_replicas);
//        try {
//            JSONArray spec_selector = spec.getJSONArray("selector");  //spec_selector
//            spec_selector_map = parse.jsonArrayToMap(spec_selector);
//        } catch (JSONException e){
//            spec_selector_map = new HashMap<String, String>();
//        }
//        System.out.println("-----------spec_selector------------ > " + spec_selector_map);
//        try {
//            JSONArray spec_temp_meta_labels = spec.getJSONObject("template").getJSONObject("metadata").getJSONArray("labels");
//            spec_temp_meta_labels_map = parse.jsonArrayToMap(spec_temp_meta_labels);  //spec_template_metadata_metadata_labels
//        } catch (JSONException e){
//            spec_temp_meta_labels_map = new HashMap<String, String>();
//        }
//        System.out.println("-----------spec_template_metadata_labels--------- >" + spec_temp_meta_labels_map);
//        JSONObject spec_temp_spec = spec.getJSONObject("template").getJSONObject("spec");  // spec_temp_spec
//        System.out.println("-----------spec_template_spec------------------ >" + spec_temp_spec);
//        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
//        String spec_temp_spec_con_name = spec_temp_spec.getJSONObject("containers").getString("name");  // spec_temp_spec_con_name
//        System.out.println("-----------spec_temp_spec_con_name------------ >" + spec_temp_spec_con_name);
//        String spec_temp_spec_con_image = spec_temp_spec.getJSONObject("containers").getString("image");  // spec_temp_spec_con_image
//        System.out.println("-----------spec_temp_spec_con_image------------ >" + spec_temp_spec_con_image);
//        try {
//            spec_temp_spec_con_imagepull = spec_temp_spec.getJSONObject("containers").getString("imagePullPolicy");
//        } catch (JSONException e){
//            spec_temp_spec_con_imagepull = null;
//        }
//        System.out.println("-----------spec_temp_spec_con_imagepullpolicy--------- > " + spec_temp_spec_con_imagepull);
//        try {
//            spec_temp_spec_con_workingDir = spec_temp_spec.getJSONObject("containers").getString("workingDir");
//        } catch (JSONException e){
//            spec_temp_spec_con_workingDir = null;
//        }
//        System.out.println("-----------spec_temp_spec_con_workingDir----------- >" + spec_temp_spec_con_workingDir);
//        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo11111111");
//        try {
//            JSONArray command = spec_temp_spec.getJSONObject("containers").getJSONArray("command");// spec_con_command
//            spec_temp_spec_con_command_list = parse.jsonArrayToList(command);
//        } catch (JSONException e ){
//            spec_temp_spec_con_command_list = new ArrayList<String>();
//        }
//        System.out.println("-------------spec_temp_spec_con_command----------------- > " + spec_temp_spec_con_command_list);
//
//        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo222222222");
//
//        try {
//            JSONArray spec_temp_spec_con_volumeMounts = spec_temp_spec.getJSONObject("containers").getJSONArray("volumeMounts");
//            spec_temp_spec_con_volumeMount_list = parse.volumeMountslist(spec_temp_spec_con_volumeMounts);
//        } catch (JSONException e){
//            spec_temp_spec_con_volumeMount_list = new ArrayList<VolumeMount>();
//        }
//        System.out.println("-----------spec_temp_spec_con_volumeMounts---------- > " + spec_temp_spec_con_volumeMount_list);
//        try {
//            JSONArray spec_temp_spec_con_ports = spec_temp_spec.getJSONObject("containers").getJSONArray("ports");
//            containerPorts_list = parse.portlist(spec_temp_spec_con_ports);
//        } catch (JSONException e){
//            containerPorts_list = new ArrayList<ContainerPort>();
//        }
//        System.out.println("----------spec_temp_spec_con_ports----------------- > " + containerPorts_list);
//        try {
//            JSONArray spec_temp_spec_con_env = spec_temp_spec.getJSONObject("containers").getJSONArray("env");
//            envVar_list = parse.envlist(spec_temp_spec_con_env);
//        } catch (JSONException e){
//            envVar_list = new ArrayList<EnvVar>();
//        }
//        System.out.println("----------spec_temp_spec_con_env------------------- > " + envVar_list);
//        try {
//            JSONArray spec_temp_spec_volumes = spec_temp_spec.getJSONArray("volumes");
//            volumes_list = parse.volumeslist(spec_temp_spec_volumes);
//        } catch (JSONException e){
//            volumes_list = new ArrayList<Volume>();
//        }
//        System.out.println("-----------spec_temp_spec_volumes------------------ > " + volumes_list);
//        System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
//        try {
//            spec_temp_spec_restartPolicy = spec_temp_spec.getString("restartPolicy");
//        } catch (JSONException e){
//            spec_temp_spec_restartPolicy = null;
//        }
//        System.out.println("----------spec_temp_spec_restartPolicy----------------  > " + spec_temp_spec_restartPolicy);
//        try {
//            spec_temp_spec_dnsPolicy = spec_temp_spec.getString("dnsPolicy");
//        } catch (JSONException e){
//            spec_temp_spec_dnsPolicy = null;
//        }
//        System.out.println("-----------spec_temp_spec_dnsPolicy--------------------  > " + spec_temp_spec_dnsPolicy );





        // Test Post  Pod
//        String abc = "{\"kind\":\"Pod\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"metadanginx\", \"namespace\":\"testdefult\", " +
//                "\"labels\":[{\"abc\":\"abc\", \"iii\":\"ooo\"}]}, \"spec\":{\"containers\":{\"name\":\"nginx\", \"image\":" +
//                "\"index.alauda.cn/kiwenlau/nginx:1.9.7\", \"imagePullPolicy\":\"Always\", \"command\": [\"echo\", \"echo1\"]," +
//                "\"workingDir\":\"/usr/local\", \"volumeMounts\":[{\"name\":\"data\", \"mountPath\":\"/usr/local\", \"readOnly\":\"true\"}," +
//                "{\"name\":\"data1\", \"mountPath\":\"/usr/local1\", \"readOnly\":\"false\"}], \"ports\":[{\"name\":\"portsName\"," +
//                "\"containerPort\":\"80\", \"protocol\":\"TCP\"}, {\"name\":\"portsName1\", \"containerPort\":\"88\", \"protocol\":\"UDP\"}]," +
//                "\"env\":[{\"name\":\"aaa\", \"value\":\"bbb\"}, {\"name\":\"ccc\",\"value\":\"ddd\"}], \"resources\":{\"limits\":{" +
//                "\"cpu\":\"0.5\", \"memory\":\"128Mi\"}}}, \"volumes\":[{\"name\":\"data\", \"hostPath\":{\"path\": \"/usr/local/docker\"}}," +
//                "{\"name\":\"data1\", \"hostPath\":{\"path\": \"/usr/local/docker1\"}}], \"restartPolicy\":\"Never\", \"dnsPolicy\":" +
//                "\"Default\"}}";
//        JSONObject json = JSONObject.fromObject(abc);


//        String NameSpacesJson = "{\"kind\":\"Namespace\", \"apiVersion\":\"v1\", \"metadata\":{\"name\":\"javaabc\"}}";
//        JSONObject json = JSONObject.fromObject(NameSpacesJson);

//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++1");
//        Map<String, String> metadata_labels_map ;  // metadata_labels
//        String spec_con_imagePullPolicy;  //spec_con_imagePullPolicy
//        List<String> spec_con_command_list ; //spec_con_command_list;
//        String spec_con_workingDir;  // spec_con_workingDir
//        List<VolumeMount> spec_con_volumeMount_list;  // spec_con_volumeMount
//        List<ContainerPort> containerPorts_list = new ArrayList<ContainerPort>(); // spec.containers.ports
//        List<EnvVar> envVar_list;  // spec.containers.env
//        List<Volume> volumes_list;  //spec.volumes
//        String spec_restartPolicy;
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++2");
//        System.out.println("--------------------------1");
//        JSONObject metadata_name = json.getJSONObject("metadata");
//        String metadata_name1 = metadata_name.getString("name");
//        System.out.println("oooooooooooooooooooooooo  " + metadata_name1);
//        try {
//            JSONArray metadata_labels = metadata_name.getJSONArray("labels");
//            metadata_labels_map = parse.jsonArrayToMap(metadata_labels);   // metadata_labels
//        }catch (JSONException e){
//            metadata_labels_map = new HashMap<String, String>();
//        }
////        System.out.println("------------the labels size--------------- >" + metadata_labels_map.size());
////        Map<String, String> metadata_labels_map = parse.jsonArrayToMap(metadata_labels);
//        System.out.println("------------metadata_labels--------------  " + metadata_labels_map);
//        JSONObject containers = json.getJSONObject("spec").getJSONObject("containers");  // spec_con
////        System.out.println("--------------------------22 " + containers);
//        String spc_con_name = containers.getString("name");  // spec_con_name
//        String spc_con_images = containers.getString("image");   // spec_con_image
//        System.out.println("-----------spc_con_name---------------  >" + spc_con_name);
//        System.out.println("-----------spc_con_imaegs---------------  >" + spc_con_images);
//        System.out.println("--------------------------3");
//        try {
//            spec_con_imagePullPolicy = containers.getString("imagePullPolicy");  // spec_containers_imaegPullPolicy
//        } catch (JSONException e){
//            spec_con_imagePullPolicy = null;
//        }
//        System.out.println("-----------spec_con_imagePullpolicy---------  >"  + spec_con_imagePullPolicy);
//        try {
//            JSONArray command = containers.getJSONArray("command"); // spec_con_command
//            spec_con_command_list = parse.jsonArrayToList(command);
//        } catch (JSONException e){
//            spec_con_command_list = new ArrayList<String>();
//        }
//        System.out.println("----------spec_con_command_list------------------ >" + spec_con_command_list);
//        try {
//            spec_con_workingDir = containers.getString("workingDir");  //spec_con_workingDir
//        } catch (JSONException e){
//            spec_con_workingDir = null;
//        }
//        System.out.println("----------sepc_con_workingDir-------------------  >" + spec_con_workingDir);
//        System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//        try {
//            JSONArray volumeMounts = containers.getJSONArray("volumeMounts");
//            spec_con_volumeMount_list = parse.volumeMountslist(volumeMounts);  // spec_con_volumeMounts
//        } catch (JSONException e){
//            spec_con_volumeMount_list = new ArrayList<VolumeMount>();
//        }
//        for(int o=0; o< volumeMounts.size(); o++){
//            JSONObject jsonObject = JSONObject.fromObject(volumeMounts.get(o));
//            System.out.println("-------spec_con_volumeMounts_name------------ >" + jsonObject.getString("name") );
//            System.out.println("-------spec_con_volumeMounts_mountPath------------ >" + jsonObject.getString("mountPath") );
//            System.out.println("-------spec_con_volumeMounts_readOnly------------ >" + jsonObject.getString("readOnly") );
//            System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//            VolumeMount volumeMount = new VolumeMount(); // spec.contianers.volumeMounts
//            volumeMount.setName((String)jsonObject.getString("name"));
//            volumeMount.setMountPath((String)jsonObject.getString("mountPath"));
//            volumeMount.setReadOnly(Boolean.getBoolean(jsonObject.getString("readOnly")));
//            spec_con_volumeMount_list.add(volumeMount);
//            System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//            System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//        }
//
//        System.out.println("-------------spec_con_volumeMount-------------------  >" + spec_con_volumeMount_list);
////        try {
//            JSONArray ports = containers.getJSONArray("ports");
////            containerPorts_list = parse.portlist(ports);  // spec_con_ports
////        } catch (JSONException e){
////            containerPorts_list = new ArrayList<ContainerPort>();
////        }
//        for(int o=0; o<ports.size(); o++){
//            JSONObject jsonObject = JSONObject.fromObject(ports.get(o));
//            System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//            System.out.println("--------spec_con_port_name--------------- > > " + jsonObject.getString("name"));
//            System.out.println("--------spec_con_port_containerPort--------------- > > " + jsonObject.getString("containerPort"));
//            System.out.println("--------spec_con_port_protocol--------------- > > " + jsonObject.getString("protocol"));
//            System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//            System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//            ContainerPort containerPort = new ContainerPort();
//            containerPort.setName((String)jsonObject.getString("name"));
//            containerPort.setContainerPort(Integer.valueOf(jsonObject.getString("containerPort")));
//
//            containerPort.setProtocol((String)jsonObject.getString("protocol")); // TCP(default)  UDP
//            containerPorts_list.add(containerPort);
////            System.out.println("oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//        }
//        System.out.println("------------spec_con_ports-------------------------- >" + containerPorts_list);
//        try {
//            JSONArray env = containers.getJSONArray("env"); // spec_con_env
//            envVar_list = parse.envlist(env);
//        } catch (JSONException e){
//            envVar_list = new ArrayList<EnvVar>();
//        }
//        for(int o=0; o<env.size(); o++){
//            JSONObject  jsonObject = JSONObject.fromObject(env.get(o));
//            System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//            System.out.println("----------spec_con_env_name-------------  > "  + jsonObject.getString("name") );
//            System.out.println("----------spec_con_env_value-------------  > "  + jsonObject.getString("value") );
//            System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//            EnvVar envVar = new EnvVar();
//            envVar.setName((String)jsonObject.getString("name"));
//            envVar.setValue((String)jsonObject.getString("value"));
//            envVar_list.add(envVar);
//        }
//        System.out.println("---------------------------------------------->>>>> "  + env);
////        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
//        System.out.println("-------------spec_con_env--------------------  > " + envVar_list);
//        System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
////        JSONObject resources = containers.getJSONObject("resources").getJSONObject("limits");  // spec_con_resources
//        System.out.println("--------------spec_con_resources_limits------------  > " + resources);
//        System.out.println("--------------spec_con_resources_limits_cpu------------ > " + resources.getString("cpu"));
//        System.out.println("--------------spec_con_resources_limits_memory------------ > " + resources.getString("memory"));
//        ResourceRequirements resourceRequirements = new ResourceRequirements();  // spec.contaiers.resources
//        Map<String, Quantity> limit_map = new HashMap<String, Quantity>();
//        Quantity quantity = new Quantity();
//        quantity.setAmount((String)resources.getString("cpu"));
//        quantity.setFormat((String)resources.getString("memory"));
//        limit_map.put("limits", quantity);
//        resourceRequirements.setLimits(limit_map);
//        try {
//            JSONArray volumes = json.getJSONObject("spec").getJSONArray("volumes");  // spec_volumes
//            volumes_list = parse.volumeslist(volumes);
//        } catch (JSONException e){
//            volumes_list = new ArrayList<Volume>();
//        }
//        System.out.println("-------------spec_volumes--------------  > " + volumes);
//        for(int o=0; o<volumes.size(); o ++){
//            System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
//            System.out.println("-------------------   > "  + JSONObject.fromObject(volumes.get(o)));
//            System.out.println("-------spec_volumes_name------------ >" + JSONObject.fromObject(volumes.get(o)).getString("name"));
//            System.out.println("-------spec_volumes_name------------ >" + JSONObject.fromObject(JSONObject.fromObject(volumes.get(o)).getString("hostPath")).getString("path"));
//            System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
//            Volume volume = new Volume();
//            volume.setName((String)JSONObject.fromObject(volumes.get(o)).getString("name"));
//            HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
//            hostPathVolumeSource.setPath((String)JSONObject.fromObject(JSONObject.fromObject(volumes.get(o)).getString("hostPath")).getString("path"));
//            volume.setHostPath(hostPathVolumeSource);
//            volumes_list.add(volume);
//        }
//        System.out.println("-----------spec_volumes---------------  >"  + volumes_list);
//        spec_restartPolicy = json.getJSONObject("spec").getString("restartPolicy");  // spec_restartpolicy
//        System.out.println("------------spec_restartPolicy--------------- >" + spec_restartPolicy);
//        spec_dnsPolicy = json.getJSONObject("spec").getString("dnsPolicy");
//        System.out.println("------------spec_dnsPolicy--------------  > " + spec_dnsPolicy);















//        System.out.println("---------------------------------------------------1");
//        Map<String, Object> PodMap = new HashMap<String, Object>();
//        List<String> spec_con_command_list = new ArrayList<String>();
//        spec_con_command_list.add("aaaa");
//        spec_con_command_list.add("bbbb");
//        PodMap.put("spec_con_command", spec_con_command_list);  //spec_con_command
//
//        List<VolumeMount> spec_con_volumeMount_list = new ArrayList<VolumeMount>();
//        VolumeMount volumeMount = new VolumeMount(); // spec.contianers.volumeMounts
//        volumeMount.setName("volumename");
//        volumeMount.setMountPath("/usr/local");
//        volumeMount.setReadOnly(true);
//        spec_con_volumeMount_list.add(volumeMount);
//        PodMap.put("spec_con_volumeMounts", spec_con_volumeMount_list);  // spec_con_volumeMounts

//        List<EnvVar> spec_con_env_list = new ArrayList<EnvVar>();
//        EnvVar envVar = new EnvVar();
//        envVar.setName("aaaa");
//        envVar.setValue("bbbb");
//        spec_con_env_list.add(envVar);
//        PodMap.put("spec_con_env", spec_con_env_list); // spec_con_env
//        System.out.println("---------------------------------------------------2");
//        System.out.println("======================> " + PodMap);
//        System.out.println("======================>1  " + PodMap.get("spec_con_command"));
//        System.out.println("======================>2  " + PodMap.get("spec_con_volumeMounts").getClass());
//        System.out.println("======================>3  " + PodMap.get("spec_con_env").getClass());
//
//        List<String>  l = (ArrayList<String>)PodMap.get("spec_con_command");
//        System.out.println("------------------------------" + l.get(0));
//        System.out.println("------------------------------" + l.get(1));
//        List<VolumeMount>  ll = (ArrayList<VolumeMount>)PodMap.get("spec_con_volumeMounts");
//        System.out.println("-------------------------------------------okokokok");
//        System.out.println("------------------------------" + ll);
//        System.out.println("-------------------------------------------ends");




//        HashMap<String, String> map = new HashMap<String, String>();
//        String jsonStr="[{\"abc\":\"abc\"},{\"ooo\":\"iii\"}]";
//        JSONArray jsonArr=JSONArray.fromObject(jsonStr);
//        System.out.println("----------------------1 ->" + jsonArr);
//        System.out.println("----------------------2 ->" + jsonArr.size());
//        for(int i=0;i<jsonArr.size();i++){
//            JSONObject obj = JSONObject.fromObject(jsonArr.get(i));
//            Iterator it = obj.keys();
//            while (it.hasNext()){
//                String key = String.valueOf(it.next());
//                String value = (String) obj.get(key);
//                map.put(key, value);
//            }
//        }
//        System.out.println(map);
//
//        HashMap<String, String> map1 = new HashMap<String, String>();
//        map1.put("aa", "bb");
//        map1.put("ii", "ll");
//        System.out.println(map1);


//        String json = "{\"tenantName\": \"dzcloud-admin\",\"passwordCredentials\": {\"username\": \"dzcloud-admin\",\"password\": \"111111\"}}";
//        String json1 = json.split(",")[0];
//        System.out.println("------------------------json  " +json1);
//        JSONObject object = JSONObject.fromObject(json);
//        System.out.println("-------------1 " + object);
//        String abc;
//        try {
//            abc =  object.getString("tenantNamea");
//        } catch (JSONException e){
//            abc = "";
//        }
//        System.out.println("------------------------" + abc);
//        System.out.println("================" + abc);
//        System.out.println("-------------3 " + object.toString());
//        Client c = Client.create();
//        WebResource r=c.resource("http://localhost:8080/RestDemo6/api/v1/namespaces/json");
//        JSONObject obj = new JSONObject();
//        obj.put("a", "1");
//        obj.put("b", "2");
//        JSONObject response = r.post(JSONObject.class, obj);
//        System.out.println(response.toString());
    }
}
