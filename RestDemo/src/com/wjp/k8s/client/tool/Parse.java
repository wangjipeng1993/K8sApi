package com.wjp.k8s.client.tool;

import com.wjp.k8s.client.impl.K8sRestfulClient;
import io.fabric8.kubernetes.api.model.PodList;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONException;
import org.apache.commons.collections.map.HashedMap;
import io.fabric8.kubernetes.api.model.*;
import org.omg.PortableServer.POA;

import java.util.*;

/**
 * Created by root on 16-11-22.
 */
public class Parse {
//    JSONObject json;

//    K8sRestfulClient k8s = new K8sRestfulClient();

    // parse Namespaces
    public String ParseNamespace(String parse){
        JSONObject json = JSONObject.fromObject(parse);
        String endstr;
        try {
            endstr = JSONObject.fromObject(json.getString("metadata")).getString("name");
        } catch (JSONException e){
            endstr = "";
        }
        return endstr;
    }

    // parse pod
    public Map ParsePod(String parsepod){
        // the yaml parameter
        String spec_con_imagePullPolicy;  //spec_con_imagePullPolicy
        List<String> spec_con_command_list;
        String spec_con_workingDir;  // spec_con_workingDir
        List<VolumeMount> spec_con_volumeMount_list ;
        List<ContainerPort> spec_con_ports_list ; // spec.containers.ports
        List<EnvVar> spec_con_env_list;  // spec.containers.env
        List<Volume> spec_volumes_list;  //spec_volumes
        String spec_restartPolicy;
        String spec_dnsPolicy;
        // create the PodMap
        Map<String, Object> PodMap = new HashMap<String, Object>();
        JSONObject json = JSONObject.fromObject(parsepod);
        JSONObject metadata_name = json.getJSONObject("metadata");
        String metadata_name1 = metadata_name.getString("name");
        PodMap.put("metadata_name", metadata_name1);
        JSONArray metadata_labels = metadata_name.getJSONArray("labels");
        Map<String, String> metadata_labels_map = jsonArrayToMap(metadata_labels);
        PodMap.put("metadata_labels", metadata_labels_map);  // metadata_labels
        JSONObject containers = json.getJSONObject("spec").getJSONObject("containers");
        String spc_con_name = containers.getString("name");
        PodMap.put("spec_con_name", spc_con_name);  // spec_con_name
        String spc_con_images = containers.getString("image");
        PodMap.put("spec_con_images", spc_con_images);  // spec_con_image
        try {
            spec_con_imagePullPolicy = containers.getString("imagePullPolicy");  // spec_containers_imaegPullPolicy
        } catch (JSONException e){
            spec_con_imagePullPolicy = null;
        }
        PodMap.put("spec_con_imagePullPolicy", spec_con_imagePullPolicy); // spec_con_imagePullPolicy
        try {
            JSONArray command = containers.getJSONArray("command"); // spec_con_command
            spec_con_command_list = jsonArrayToList(command);
        } catch (JSONException e){
            spec_con_command_list = new ArrayList<String>();
        }
        PodMap.put("spec_con_command", spec_con_command_list);  //spec_con_command
        try {
            spec_con_workingDir = containers.getString("workingDir");  //spec_con_workingDir
        } catch (JSONException e){
            spec_con_workingDir = null;
        }
        PodMap.put("spec_con_workingDir", spec_con_workingDir); //spec_con_workingDir
        try {
            JSONArray volumeMounts = containers.getJSONArray("volumeMounts");
            spec_con_volumeMount_list = volumeMountslist(volumeMounts);  // spec_con_volumeMounts
        } catch (JSONException e){
            spec_con_volumeMount_list = new ArrayList<VolumeMount>();
        }
        PodMap.put("spec_con_volumeMounts", spec_con_volumeMount_list);  // spec_con_volumeMounts
        try {
            JSONArray ports = containers.getJSONArray("ports");
            spec_con_ports_list = portlist(ports);  // spec_con_ports
        } catch (JSONException e){
            spec_con_ports_list = new ArrayList<ContainerPort>();
        }
        PodMap.put("spec_con_ports", spec_con_ports_list);  //spec_con_ports
        try {
            JSONArray env = containers.getJSONArray("env"); // spec_con_env
            spec_con_env_list = envlist(env);
        } catch (JSONException e){
            spec_con_env_list = new ArrayList<EnvVar>();
        }
        PodMap.put("spec_con_env", spec_con_env_list); // spec_con_env
        // unknow resources
//        Map<String, Quantity> spec_con_resource = new HashMap<String, Quantity>();
//        JSONObject resources = containers.getJSONObject("resources").getJSONObject("limits");  // spec_con_resources
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
//        PodMap.put("spec_con_resource", spec_con_resource);  //spec_con_resource
        try {
            JSONArray volumes = json.getJSONObject("spec").getJSONArray("volumes");  // spec_volumes
            spec_volumes_list = volumeslist(volumes);
        } catch (JSONException e){
            spec_volumes_list = new ArrayList<Volume>();
        }
        PodMap.put("spec_volumes", spec_volumes_list); //spec.volumes
        try {
            spec_restartPolicy = json.getJSONObject("spec").getString("restartPolicy");  // spec_restartpolicy
        } catch (JSONException e){
            spec_restartPolicy = null;
        }
        PodMap.put("spec_restartpolicy", spec_restartPolicy);  //spec_restartpolicy
        try {
            spec_dnsPolicy = json.getJSONObject("spec").getString("dnsPolicy");
        } catch (JSONException e){
            spec_dnsPolicy = null;
        }
        PodMap.put("spec_dnsPolicy", spec_dnsPolicy); // spec_dnsPolicy
        return PodMap;
    }


    // parse RC
    public Map ParseRC(String parserc){
        // the yaml parameter
        Map<String, String> metadata_labels_map ;  // metadata_labels
        Map<String, String> spec_selector_map;  // spec_selector
        Map<String, String> spec_temp_meta_labels_map; // spec_template_metadata_labels
        String spec_temp_spec_con_imagepull; //spec_temp_spec_con_imagepullpolicy
        String spec_temp_spec_con_workingDir; //spec_temp_spec_con_workingDir
        List<String> spec_temp_spec_con_command_list; //spec_temp_spec_con_command
        List<VolumeMount> spec_temp_spec_con_volumeMount_list;  //spec_temp_spec_con_volumeMount
        List<ContainerPort> containerPorts_list;  // spec_temp_spec_con_ports
        List<EnvVar> envVar_list;  // spec_temp_spec_con_env
        List<Volume> volumes_list;  // spec_temp_spec_volumes
        String spec_temp_spec_restartPolicy; //spec_temp_spec_restartPolicy
        String spec_temp_spec_dnsPolicy;  // spec_temp_spec_dnsPolicy
        // parse pod
        Map<String, Object> RcMap = new HashMap<String, Object>();
        JSONObject json = JSONObject.fromObject(parserc);
        JSONObject metadata = json.getJSONObject("metadata");
        String metadata_name = metadata.getString("name");  // metadata_name
        RcMap.put("metadata_name", metadata_name);
        try {
            JSONArray metadata_labels = metadata.getJSONArray("labels");
            metadata_labels_map = jsonArrayToMap(metadata_labels);   // metadata_labels
        }catch (JSONException e){
            metadata_labels_map = new HashMap<String, String>();
        }
        RcMap.put("metadata_labels", metadata_labels_map);
        JSONObject spec = json.getJSONObject("spec");  //spec
        String spec_replicas = spec.getString("replicas");
        RcMap.put("spec_replicas", spec_replicas);  // spec_replicas
        try {
            JSONArray spec_selector = spec.getJSONArray("selector");  //spec_selector
            spec_selector_map = jsonArrayToMap(spec_selector);
        } catch (JSONException e){
            spec_selector_map = new HashMap<String, String>();
        }
        RcMap.put("spec_selector", spec_selector_map);
        try {
            JSONArray spec_temp_meta_labels = spec.getJSONObject("template").getJSONObject("metadata").getJSONArray("labels");
            spec_temp_meta_labels_map = jsonArrayToMap(spec_temp_meta_labels);  //spec_template_metadata_metadata_labels
        } catch (JSONException e){
            spec_temp_meta_labels_map = new HashMap<String, String>();
        }
        RcMap.put("spec_temp_meta_labels", spec_temp_meta_labels_map);
        JSONObject spec_temp_spec = spec.getJSONObject("template").getJSONObject("spec");  // spec_temp_spec
        String spec_temp_spec_con_name = spec_temp_spec.getJSONObject("containers").getString("name");  // spec_temp_spec_con_name
        RcMap.put("spec_temp_spec_con_name", spec_temp_spec_con_name);
        String spec_temp_spec_con_image = spec_temp_spec.getJSONObject("containers").getString("image");  // spec_temp_spec_con_image
        RcMap.put("spec_temp_spec_con_image", spec_temp_spec_con_image);
        try {
            spec_temp_spec_con_imagepull = spec_temp_spec.getJSONObject("containers").getString("imagePullPolicy"); //spec_temp_spec_con_imagepull
        } catch (JSONException e){
            spec_temp_spec_con_imagepull = null;
        }
        RcMap.put("spec_temp_spec_con_imagepull", spec_temp_spec_con_imagepull);
        try {
            spec_temp_spec_con_workingDir = spec_temp_spec.getJSONObject("containers").getString("workingDir"); //spec_temp_spec_con_workingDir
        } catch (JSONException e){
            spec_temp_spec_con_workingDir = null;
        }
        RcMap.put("spec_temp_spec_con_workingDir", spec_temp_spec_con_workingDir);
        try {
            JSONArray command = spec_temp_spec.getJSONObject("containers").getJSONArray("command");// spec_con_command
            spec_temp_spec_con_command_list = jsonArrayToList(command);
        } catch (JSONException e ){
            spec_temp_spec_con_command_list = new ArrayList<String>();
        }
        RcMap.put("spec_temp_spec_con_command", spec_temp_spec_con_command_list);
        try {
            JSONArray spec_temp_spec_con_volumeMounts = spec_temp_spec.getJSONObject("containers").getJSONArray("volumeMounts");
            spec_temp_spec_con_volumeMount_list = volumeMountslist(spec_temp_spec_con_volumeMounts);   // spec_temp_spec_con_volumeMount
        } catch (JSONException e){
            spec_temp_spec_con_volumeMount_list = new ArrayList<VolumeMount>();
        }
        RcMap.put("spec_temp_spec_con_volumeMounts", spec_temp_spec_con_volumeMount_list);
        try {
            JSONArray spec_temp_spec_con_ports = spec_temp_spec.getJSONObject("containers").getJSONArray("ports");
            containerPorts_list = portlist(spec_temp_spec_con_ports);  //spec_temp_spec_con_ports
        } catch (JSONException e){
            containerPorts_list = new ArrayList<ContainerPort>();
        }
        RcMap.put("spec_temp_spec_con_ports", containerPorts_list);
        try {
            JSONArray spec_temp_spec_con_env = spec_temp_spec.getJSONObject("containers").getJSONArray("env");
            envVar_list = envlist(spec_temp_spec_con_env);  // spec_temp_spec_con_env
        } catch (JSONException e){
            envVar_list = new ArrayList<EnvVar>();
        }
        RcMap.put("spec_temp_spec_con_env", envVar_list);
        try {
            JSONArray spec_temp_spec_volumes = spec_temp_spec.getJSONArray("volumes");
            volumes_list = volumeslist(spec_temp_spec_volumes);  // spec_temp_spec_volumes
        } catch (JSONException e){
            volumes_list = new ArrayList<Volume>();
        }
        RcMap.put("spec_temp_spec_volumes", volumes_list);
        try {
            spec_temp_spec_restartPolicy = spec_temp_spec.getString("restartPolicy");  //spec_temp_spec_restartPolicy
        } catch (JSONException e){
            spec_temp_spec_restartPolicy = null;
        }
        RcMap.put("spec_temp_spec_restartPolicy", spec_temp_spec_restartPolicy);
        try {
            spec_temp_spec_dnsPolicy = spec_temp_spec.getString("dnsPolicy");  // spec_temp_spec_dnspolicy
        } catch (JSONException e){
            spec_temp_spec_dnsPolicy = null;
        }
        RcMap.put("spec_temp_spec_dnsPolicy", spec_temp_spec_dnsPolicy);
        return RcMap;
        //    public void createRC(String metadata_name, String metadata_namespace,Map<String, String> metadata_labels,
//                         int spec_replicas, Map<String, String> spec_selector_map, Map<String, String> temp_labels,
//                         String spec_con_name, String spec_con_image, String spec_con_imagePullPolicy, String spec_con_workingDir,
//                         List<String> spec_con_command, List<VolumeMount> spec_con_volumeMounts, List<ContainerPort> spec_con_ports,
//                         List<EnvVar> spec_con_env, List<Volume> spec_volumes, String spec_restartPolicy, String spec_dnsPolicy);
//        ((String)RcMap.get("metadata_name"), namespace, (Map<String, String>)RcMap.get("metadata_labels"), Integer.valueOf(RcMap.get("spec_replicas")),
//                (Map<String, String>)RcMap.get("spec_selector"), (Map<String, String>)RcMap.get("spec_temp_meta_labels"),
//                (String)RcMap.get("spec_temp_spec_con_name"), (String)RcMap.get("spec_temp_spec_con_image"), (String)RcMap.get("spec_temp_spec_con_imagepull"),
//                (String)RcMap.get("spec_temp_spec_con_workingDir"), (List<String>)RcMap.get("spec_temp_spec_con_command"),
//                (List<VolumeMount>)RcMap.get("spec_temp_spec_con_volumeMounts"), (List<ContainerPort>)RcMap.get("spec_temp_spec_con_ports"),
//                (List<EnvVar>)RcMap.get("spec_temp_spec_con_env"), (List<Volume>)RcMap.get("spec_temp_spec_volumes"), (String)RcMap.get("spec_temp_spec_restartPolicy"),
//                (String)RcMap.get("spec_temp_spec_dnsPolicy"));
    }


    // parse Service
    public static Map ParseService(String parseService){
        // the yaml parameter
        Map<String, String> metadata_labels_map ;  // metadata_labels
        Map<String, String> spec_selector_map;  // spec_selector
        String spec_type;  //spec_type
        String spec_clusterIP; // spec_clusterIP
        List<ServicePort> portList;  // spec_ports
        String loadbalance_ingress_ip; // spec_status_loadBalancer_ingress_ip
        String loadbalance_ingress_hostname; // spec_status_loadBalancer_ingress_hostname
        // parse Service
        Map<String, Object> ServiceMap = new HashMap<String, Object>();
        JSONObject json = JSONObject.fromObject(parseService);
        String metadata_name = json.getJSONObject("metadata").getString("name");   // metadata_name
        ServiceMap.put("metadata_name", metadata_name);
        try {
            JSONArray metadata_labels = json.getJSONObject("metadata").getJSONArray("labels");  // metadata_labels
            metadata_labels_map = jsonArrayToMap(metadata_labels);
        }catch (JSONException e){
            metadata_labels_map = new HashMap<String, String>();
        }
        ServiceMap.put("metadata_labels", metadata_labels_map);
        JSONObject spec = json.getJSONObject("spec");  // spec
        try {
            JSONArray spec_selector = spec.getJSONArray("selector");  // spec_selector
            spec_selector_map = jsonArrayToMap(spec_selector);
        } catch (JSONException e){
            spec_selector_map = new HashMap<String, String>();
        }
        ServiceMap.put("spec_selector", spec_selector_map);
        try {
            spec_type = spec.getString("type");  // spec_type
        } catch (JSONException e){
            spec_type = null;
        }
        ServiceMap.put("spec_type", spec_type);
        try {
            spec_clusterIP = spec.getString("clusterIP");  //spec_clusterIP
        } catch (JSONException e){
            spec_clusterIP = null;
        }
        ServiceMap.put("spec_clusterIP", spec_clusterIP);
        try {
            JSONArray spec_ports = spec.getJSONArray("ports");   // spec_ports
            portList = ServicePortlist(spec_ports);
        } catch (JSONException  e){
            portList = new ArrayList<ServicePort>();
        }
        ServiceMap.put("spec_ports", portList);
        try {
            loadbalance_ingress_ip = spec.getJSONObject("status").getJSONObject("loadBalancer").
                    getJSONObject("ingress").getString("ip");
        } catch (JSONException e){
            loadbalance_ingress_ip = null;
        }
        ServiceMap.put("spec_status_loadBalancer_ingress_ip", loadbalance_ingress_ip);
        try {
            loadbalance_ingress_hostname = spec.getJSONObject("status").getJSONObject("loadBalancer")
                    .getJSONObject("ingress").getString("hostname");
        } catch (JSONException e){
            loadbalance_ingress_hostname = null;
        }
        ServiceMap.put("spec_status_loadBalancer_ingress_hostname", loadbalance_ingress_hostname);
        return ServiceMap;
//        public String createService(String metadata_name, String metadata_namespace, Map<String, String> metadata_labels, Map<String, String> spec_selector,
//                String spec_type, String spec_clusterIP, List<ServicePort> portList, String status_balancer_ingress_ip,
//                 status_balancer_ingress_hostname);
//        createService((String)ServiceMap.get("metadata_name"), namespace, (Map<String, String>)ServiceMap.get("metadata_labels"),
//                (Map<String, String>)ServiceMap.get("spec_selector"), (String)ServiceMap.get("spec_type"), (String)ServiceMap.get("spec_clusterIP"),
//                (List<ServicePort>)ServiceMap.get("spec_ports"), (String)ServiceMap.get("spec_status_loadBalancer_ingress_ip"),
//                (String)ServiceMap.get("spec_status_loadBalancer_ingress_hostname"));

    }



    // JSONArray --> HashMap
    public static Map jsonArrayToMap(JSONArray jsonArray){
        HashMap<String, String> map = new HashMap<String, String>();
        JSONArray jsonArr=JSONArray.fromObject(jsonArray);
        for(int i=0;i<jsonArr.size();i++){
            JSONObject obj = JSONObject.fromObject(jsonArr.get(i));
            Iterator it = obj.keys();
            while (it.hasNext()){
                String key = String.valueOf(it.next());
                String value = (String) obj.get(key);
                map.put(key, value);
            }
        }
        return map;
    }



    // JSONArray --> Arraylist
    public static List jsonArrayToList(JSONArray jsonArray){
        List<String> list = new ArrayList<String>();
        for(int o=0; o<jsonArray.size(); o ++){
            list.add((String)jsonArray.get(o));
        }
        return list;
    }



    // spec_containers_volumeMounts
    public static List volumeMountslist(JSONArray jsonArray){
        List<VolumeMount> list = new ArrayList<VolumeMount>();
        for(int o=0; o< jsonArray.size(); o++){
            JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(o));
            VolumeMount volumeMount = new VolumeMount(); // spec.contianers.volumeMounts
            volumeMount.setName((String)jsonObject.getString("name"));
            volumeMount.setMountPath((String)jsonObject.getString("mountPath"));
            volumeMount.setReadOnly(Boolean.parseBoolean(jsonObject.getString("readOnly")));  //Boolean.parseBoolean(s1);
            list.add(volumeMount);
        }
        return list;
    }



    // spec_con_ports
    public static List portlist(JSONArray jsonArray){
        List<ContainerPort> list = new ArrayList<ContainerPort>();
        for(int o=0; o<jsonArray.size(); o++){
            JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(o));
            ContainerPort containerPort = new ContainerPort();
            containerPort.setName((String)jsonObject.getString("name"));
            containerPort.setContainerPort(Integer.valueOf(jsonObject.getString("containerPort")));
//            containerPort.setContainerPort(90);
            containerPort.setProtocol((String)jsonObject.getString("protocol")); // TCP(default)  UDP
            list.add(containerPort);
        }
        return list;
    }



    // spec_con_env
    public static List envlist(JSONArray jsonArray){
        List<EnvVar> list = new ArrayList<EnvVar>();
        for(int o=0; o<jsonArray.size(); o++){
            JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(o));
            EnvVar envVar = new EnvVar();
            envVar.setName((String)jsonObject.getString("name"));
            envVar.setValue((String)jsonObject.getString("value"));
            list.add(envVar);
        }
        return list;
    }



    // spec_volumes
    public static List volumeslist(JSONArray jsonArray){
        List<Volume> list = new ArrayList<Volume>();
        for(int o=0; o<jsonArray.size(); o ++){
            Volume volume = new Volume();
            volume.setName((String)JSONObject.fromObject(jsonArray.get(o)).getString("name"));
            HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
            hostPathVolumeSource.setPath((String)JSONObject.fromObject(JSONObject.fromObject(jsonArray.get(o)).getString("hostPath")).getString("path"));
            volume.setHostPath(hostPathVolumeSource);
            list.add(volume);
        }
        return list;
    }



    // spec_ports
    public static List ServicePortlist(JSONArray jsonArray){
        List<ServicePort> list = new ArrayList<ServicePort>();
        for(int o=0; o < jsonArray.size(); o ++){
            JSONObject spec_port_json = JSONObject.fromObject(jsonArray.get(o));
            ServicePort servicePort = new ServicePort();
            servicePort.setPort(Integer.valueOf(spec_port_json.getString("port")));
            IntOrString intOrString = new IntOrString();
            intOrString.setIntVal(Integer.valueOf(spec_port_json.getString("targetPort")));
            servicePort.setTargetPort(intOrString);
            servicePort.setProtocol(spec_port_json.getString("protocol"));
            list.add(servicePort);
        }
        return list;
    }



}
