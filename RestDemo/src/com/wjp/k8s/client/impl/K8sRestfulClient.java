package com.wjp.k8s.client.impl;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 16-11-22.
 */
public class K8sRestfulClient implements K8sApiClient{

    private static final Logger logger = LoggerFactory.getLogger(K8sRestfulClient.class);
    String master = "http://localhost:8087/";
    Config config = new ConfigBuilder().withMasterUrl(master).build();
    KubernetesClient client = new DefaultKubernetesClient(config);

    // create service
    public String createService(String metadata_name,String metadata_namespace, Map<String, String> metadata_labels, Map<String, String> spec_selector,
                              String spec_type, String spec_clusterIP, List<ServicePort> portList, String status_balancer_ingress_ip,
                              String status_balancer_ingress_hostname){
        Service service = new Service();
        service.setApiVersion(Service.ApiVersion.V_1);
        service.setKind("Service");
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(metadata_name);
//        Map<String, String> metadata_labels = new HashMap<String, String>();  // metadata.labels
//        metadata_labels.put("labels","nginx");
        if (metadata_labels.size() !=0) {
            objectMeta.setLabels(metadata_labels);
        } else {
            logger.info(" the service metadata.labels is empty!!");
        }
        service.setMetadata(objectMeta);
        ServiceSpec serviceSpec = new ServiceSpec();
//        Map<String, String> spec_selector = new HashMap<String, String>();  // spec.selector
//        spec_selector.put("selector", "nginx");
        if(spec_selector.size() != 0) {
            serviceSpec.setSelector(spec_selector);
        } else{
            logger.info(" the service spec.selector is empty!!");
        }
        if(spec_type == null) {
            serviceSpec.setType("ClusterIP");  // spec.type
        }else{
            serviceSpec.setType(spec_type);
        }
        if(spec_clusterIP != null) {
            serviceSpec.setClusterIP(spec_clusterIP);  // spec.clusterip
        }
        //        List<ServicePort> portList = new ArrayList<ServicePort>();  // spec.ports
//        ServicePort servicePort = new ServicePort();
//        servicePort.setName("serviceport");
//        servicePort.setPort(80);
//        IntOrString intOrString = new IntOrString();
//        intOrString.setIntVal(80);
//        servicePort.setTargetPort(intOrString);
//        servicePort.setProtocol("TCP");
//        portList.add(servicePort);
        if(portList.size() != 0) {
            serviceSpec.setPorts(portList);
        } else{
            logger.info(" the service spec.ports is empty!!");
        }
        service.setSpec(serviceSpec);
        ServiceStatus serviceStatus = new ServiceStatus();  // sepc.status
        List<LoadBalancerIngress>  loadBalancerIngresses_list = new ArrayList<LoadBalancerIngress>();  //
        LoadBalancerStatus loadBalancerStatus = new LoadBalancerStatus();
        LoadBalancerIngress loadBalancerIngress = new LoadBalancerIngress();
        if(status_balancer_ingress_ip != null) {
            loadBalancerIngress.setIp(status_balancer_ingress_ip);
        }else{
            logger.info(" the service spec.status.loadBalance.ingress.ip is empty!!");
        }
        if(status_balancer_ingress_hostname != null) {
            loadBalancerIngress.setHostname(status_balancer_ingress_hostname);
        }else{
            logger.info(" the service spec.status.loadBalance.ingress.hostname is empty!!");
        }
        loadBalancerIngresses_list.add(loadBalancerIngress);
        loadBalancerStatus.setIngress(loadBalancerIngresses_list);
        serviceStatus.setLoadBalancer(loadBalancerStatus);
        service.setStatus(serviceStatus);
        if(metadata_namespace == null) {
            try {
                client.services().inNamespace("default").create(service);
                logger.info(" the service  is create ok !!");
                client.close();
                return "the service " +metadata_name +" is create ok  !!";
            } catch (KubernetesClientException e){
                logger.info(" the service crete failed!!   "  + e);
                client.close();
                return "the service " +metadata_name +" is failed  !!";
            }
        } else {
            try {
                client.services().inNamespace(metadata_namespace).create(service);
                logger.info(" the service  is create ok !!");
                client.close();
                return "the service " +metadata_name +" is create ok  !!";
            } catch (KubernetesClientException e){
                logger.info(" the service crete failed!!   " + e);
                client.close();
                return "the service " +metadata_name +" is failed  !!";
            }
        }
//        client.close();
//        client.services().inNamespace("default").create(service);
    }


    // create rc
    public String createRC(String metadata_name, String metadata_namespace,Map<String, String> metadata_labels,
                         String spec_replicas, Map<String, String> spec_selector_map, Map<String, String> temp_labels,
                         String spec_con_name, String spec_con_image, String spec_con_imagePullPolicy, String spec_con_workingDir,
                         List<String> spec_con_command, List<VolumeMount> spec_con_volumeMounts, List<ContainerPort> spec_con_ports,
                         List<EnvVar> spec_con_env, List<Volume> spec_volumes, String spec_restartPolicy, String spec_dnsPolicy) {
        ReplicationController rc = new ReplicationController();
        rc.setApiVersion(ReplicationController.ApiVersion.V_1);
        rc.setKind("ReplicationController");
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(metadata_name);
//        Map<String, String> metadata_labels = new HashMap<String, String>(); // metadata.lables
//        metadata_labels.put("iii", "ooo");
        if (metadata_labels.size() != 0) {
            objectMeta.setLabels(metadata_labels);
        } else {
            logger.info(" the rc metadata.labels is empty!!");
        }
        rc.setMetadata(objectMeta);
        ReplicationControllerSpec RcSpec = new ReplicationControllerSpec();
        RcSpec.setReplicas(Integer.valueOf(spec_replicas));
//        Map<String, String> spec_selector_map = new HashMap<String, String>(); //spec.selector
//        spec_selector_map.put("abc", "abcd");
        if (spec_selector_map.size() != 0) {
            RcSpec.setSelector(spec_selector_map);
        } else {
            logger.info(" the rc spec.selector is empty!!");
        }
        PodTemplateSpec spec_temp_spec = new PodTemplateSpec();  // spec.template
        ObjectMeta spec_temp_meta_labels = new ObjectMeta();
//        Map<String, String> temp_labels = new HashMap<String, String>();
//        temp_labels.put("abc", "abcd");
        if (temp_labels.size() != 0) {
            spec_temp_meta_labels.setLabels(temp_labels);
        } else {
            logger.info(" the rc spec.temp.metadata.labels is empty!!");
        }
        spec_temp_spec.setMetadata(spec_temp_meta_labels);
        PodSpec podSpec = new PodSpec();
        List<Container> containers_list = new ArrayList<Container>();
        Container container = new Container();
        container.setName(spec_con_name);  // spec.template.spec.containers.name
        container.setImage(spec_con_image); // spec.template.spec.containers.images
        if (spec_con_imagePullPolicy == null) {
            container.setImagePullPolicy("Always"); // spec.template.spec.containers.imagePullPolicy
        } else {
            container.setImagePullPolicy(spec_con_imagePullPolicy);
        }
        if (spec_con_workingDir != null) {
            container.setWorkingDir(spec_con_workingDir); // spec.template.spec.containers.workDir
        } else {
            logger.info(" the rc spec.temp.containers.workingDir is empty!!");
        }
//        List<String> command_list = new ArrayList<String>(); // spec.containers.command
        //        command_list.add("echo hello !!");
        if (spec_con_command.size() != 0) {
            container.setCommand(spec_con_command);
        } else {
            logger.info(" the rc spec.temp.containers.command is empty!!");
        }
        //        List<VolumeMount> volumeMount_list = new ArrayList<VolumeMount>();
//        VolumeMount volumeMount = new VolumeMount(); // spec.template.spec.containers.volumeMounts
//        volumeMount.setName("volumename");
//        volumeMount.setMountPath("/usr/local");
//        volumeMount.setReadOnly(true);
//        volumeMount_list.add(volumeMount);
        if (spec_con_volumeMounts.size() != 0) {
            container.setVolumeMounts(spec_con_volumeMounts);
        } else {
            logger.info(" the rc spec.temp.containers.volumeMounts is empty!!");
        }
//        List<ContainerPort> containerPorts_list = new ArrayList<ContainerPort>(); // spec.template.spec.containers.ports
//        ContainerPort containerPort = new ContainerPort();
//        containerPort.setName("containerports");
//        containerPort.setContainerPort(80);
//        containerPort.setProtocol("TCP");  // TCP(default)  UDP
//        containerPorts_list.add(containerPort);
        if (spec_con_ports.size() != 0) {
            container.setPorts(spec_con_ports);
        } else {
            logger.info(" the rc spec.temp.containers.ports is empty!!");
        }
//        List<EnvVar> envVar_list = new ArrayList<EnvVar>();  // spec.template.spec.containers.env
//        EnvVar envVar = new EnvVar();
//        envVar.setName("aaaa");
//        envVar.setValue("bbbb");
//        envVar_list.add(envVar);
        if (spec_con_env.size() != 0) {
            container.setEnv(spec_con_env);
        } else {
            logger.info(" the rc spec.temp.containers.env is empty!!");
        }
        //        ResourceRequirements resourceRequirements = new ResourceRequirements();  // spec.template.spec.containers.resources
//        Map<String, Quantity> limit_map = new HashMap<String, Quantity>();
//        Quantity quantity = new Quantity();
//        quantity.setAmount("0.5");
//        quantity.setFormat("128Mi");
//        limit_map.put("limits", quantity);
//        resourceRequirements.setLimits(limit_map);
//        container.setResources(resourceRequirements);
        containers_list.add(container);
        podSpec.setContainers(containers_list);
//        List<Volume> volumes_list = new ArrayList<Volume>();  //spec.volumes
//        Volume volume = new Volume();
//        volume.setName("volumename");
//        HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
//        hostPathVolumeSource.setPath("/usr/local/docker");
//        volume.setHostPath(hostPathVolumeSource);
//        volumes_list.add(volume);
        if (spec_volumes.size() != 0) {
            podSpec.setVolumes(spec_volumes);
        } else {
            logger.info(" the rc spec.temp.volumes is empty!!");
        }
        if (spec_restartPolicy == null) {
            podSpec.setRestartPolicy("Always");  // spec.restartPollicy (Always(default), OnFailure, Never)
        } else {
            podSpec.setRestartPolicy(spec_restartPolicy);
        }
        if (spec_dnsPolicy == null) {
            podSpec.setDnsPolicy("Default"); // spec.dnsPolicy (Default, ClusterFirst)
        } else {
            podSpec.setDnsPolicy(spec_dnsPolicy);
        }
        spec_temp_spec.setSpec(podSpec);
        RcSpec.setTemplate(spec_temp_spec);
        rc.setSpec(RcSpec);
        if (metadata_namespace == null) {
            try {
                client.replicationControllers().inNamespace("default").create(rc);
                logger.info(" the rc  is create ok !!");
                client.close();
                return " the rc " + metadata_name + " is create ok !!";
            } catch (KubernetesClientException e) {
                logger.info(" the rc crete failed!!   " + e);
                client.close();
                return " the rc create " + metadata_name + " is failed !!";
            }
        } else {
            try {
                client.replicationControllers().inNamespace(metadata_namespace).create(rc);
                logger.info(" the rc  is create ok !!");
                client.close();
                return " the rc " + metadata_name + " is create ok !!";
            } catch (KubernetesClientException e) {
                logger.info(" the rc crete failed!!   " + e);
                client.close();
                return " the rc create " + metadata_name + " is failed !!";
            }
        }
//        client.close();
    }



    // create pod
    public String createPod(String metadata_name, String metadata_namespace, Map<String , String> metadata_labels,
                          String spec_con_name, String spec_con_image, String spec_con_imagePullPolicy,
                          List<String> spec_con_command, String spec_con_workingDir, List<VolumeMount> spec_con_volumeMount,
                          List<ContainerPort> spec_con_Ports, List<EnvVar> spec_con_env, List<Volume> spec_volumes,
                          String spec_restartPolicy, String spec_dnsPolicy){
        Pod pod =new Pod();
        pod.setApiVersion(Pod.ApiVersion.V_1);
        pod.setKind("Pod");
        ObjectMeta om = new ObjectMeta(); //metadata.labels
        om.setName(metadata_name);
//        Map<String , String> metadata_labels = new HashMap<String, String>();
//        metadata_labels.put("abc", "abcd");
        if(metadata_labels.size() != 0) {
            om.setLabels(metadata_labels);
        }else {
            logger.info(" the pod metadata.labels is empty!!");
        }
        pod.setMetadata(om);
        PodSpec podSpec = new PodSpec();
        List<Container> Container_list = new ArrayList<Container>();
        Container container = new Container();
        container.setName(spec_con_name);  //spec.containers.name
        container.setImage(spec_con_image);  // spec.contianers.images
        if (spec_con_imagePullPolicy == null) {
            container.setImagePullPolicy("Always"); // spec.containers.imagepullPolicy  (Always(default, IfNotPresent, Never))
        }else{
            container.setImagePullPolicy(spec_con_imagePullPolicy);
        }
        //        List<String> command_list = new ArrayList<String>(); // spec.containers.command
//        command_list.add("echo hello !!");
        if(spec_con_command.size() !=0 ) {
            container.setCommand(spec_con_command);
        }else{
            logger.info(" the pod spec.containers.command is empty!!");
        }
        if(spec_con_workingDir != null) {
            container.setWorkingDir(spec_con_workingDir); // spec.containers.workingDir
        }else{
            logger.info(" the pod spec.containers.workingDir is empty!!");
        }
//        List<VolumeMount> volumeMount_list = new ArrayList<VolumeMount>();
//        VolumeMount volumeMount = new VolumeMount(); // spec.contianers.volumeMounts
//        volumeMount.setName("volumename");
//        volumeMount.setMountPath("/usr/local");
//        volumeMount.setReadOnly(true);
//        volumeMount_list.add(volumeMount);
        if(spec_con_volumeMount.size() != 0) {
            container.setVolumeMounts(spec_con_volumeMount);
        } else{
            logger.info(" the pod spec.containers.volumeMounts is empty!!");
        }
//        List<ContainerPort> containerPorts_list = new ArrayList<ContainerPort>(); // spec.containers.ports
//        ContainerPort containerPort = new ContainerPort();
//        containerPort.setName("containerports");
//        containerPort.setContainerPort(80);
//        containerPort.setProtocol("TCP");  // TCP(default)  UDP
//        containerPorts_list.add(containerPort);
        if(spec_con_Ports.size() != 0) {
            container.setPorts(spec_con_Ports);
        } else{
            logger.info(" the pod spec.containers.port is empty!!");
        }
        //        List<EnvVar> envVar_list = new ArrayList<EnvVar>();  // spec.containers.env
//        EnvVar envVar = new EnvVar();
//        envVar.setName("aaaa");
//        envVar.setValue("bbbb");
//        envVar_list.add(envVar);
        if(spec_con_env.size() != 0) {
            container.setEnv(spec_con_env);
        } else {
            logger.info(" the pod spec.containers.env is empty!!");
        }

//        ResourceRequirements resourceRequirements = new ResourceRequirements();  // spec.contaiers.resources
//       Map<String, Quantity> limit_map = new HashMap<String, Quantity>();
//        Quantity quantity = new Quantity();
//        quantity.setAmount("0.5");
//        quantity.setFormat("128Mi");
//        limit_map.put("limits", quantity);
//        resourceRequirements.setLimits(limit_map);
//        container.setResources(resourceRequirements);
        Container_list.add(container);
        podSpec.setContainers(Container_list);
//        List<Volume> volumes_list = new ArrayList<Volume>();  //spec.volumes
//        Volume volume = new Volume();
//        volume.setName("volumename");
//        HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
//        hostPathVolumeSource.setPath("/usr/local/docker");
//        volume.setHostPath(hostPathVolumeSource);
//        volumes_list.add(volume);
        if(spec_volumes.size() != 0) {
            podSpec.setVolumes(spec_volumes);
        } else{
            logger.info(" the pod spec.volumes is empty!!");
        }
        if(spec_restartPolicy == null) {
            podSpec.setRestartPolicy("Always");  // spec.restartPollicy (Always(default), OnFailure, Never)
        }else{
            podSpec.setRestartPolicy(spec_restartPolicy);
        }
        if(spec_dnsPolicy == null) {
            podSpec.setDnsPolicy("Default"); // spec.dnsPolicy (Default, ClusterFirst)
        } else{
            podSpec.setDnsPolicy(spec_dnsPolicy);
        }
        pod.setSpec(podSpec);
        if(metadata_namespace == null) {
            try {
                client.pods().inNamespace("default").create(pod);
                logger.info(" the pod  is create ok !!");
                client.close();
                return "the pod " + metadata_name + " is create ok !!\n";
            } catch (KubernetesClientException e){
                logger.info(" the pod crete failed!!   "  + e);
                client.close();
                return "the pod " + metadata_name + " is create failed !!\n";
            }
        } else {
            try {
                client.pods().inNamespace(metadata_namespace).create(pod);
                logger.info(" the pod  is create ok !!");
                client.close();
                return "the pod " + metadata_name + " is create ok !!\n";
            } catch (KubernetesClientException e){
                logger.info(" the pod crete failed!!   " + e);
                client.close();
                return "the pod " + metadata_name + " is create failed !!\n";
            }
        }
//        client.close();
    }



    //create namespace
    public String createNamespace(String namespace){
        Namespace ns = new Namespace();
        ns.setApiVersion(Namespace.ApiVersion.V_1);
        ns.setKind("Namespace");
        ObjectMeta om = new ObjectMeta();
        om.setName(namespace);
        ns.setMetadata(om);
        try {
            client.namespaces().create(ns);
            logger.info(" the namespaces " + namespace + "  create sucess!!");
            client.close();
            return " the namespaces " + namespace + "  create sucess!!";
        }catch (KubernetesClientException e){
            logger.info(" the namespaces " + namespace + "  create failed!!");
            return " the namespaces " + namespace + "  create failed!!";
        }
//        client.close();
    }


    // get namespace
    public String getNamespace(String namespaceName){
        List namespace_list = new ArrayList();
        String endstr = "";
        if(namespaceName !=null){
            for(int i=0; i< client.namespaces().list().getItems().size(); i++) {
                if(client.namespaces().list().getItems().get(i).toString().split("selfLink=/api/v1/")[0].contains(namespaceName)) {
                    System.out.println("---------------------" + client.namespaces().list().getItems().get(i));
                    namespace_list.add(client.namespaces().list().getItems().get(i));
                }
            }
        }else{
            for(int i=0; i< client.namespaces().list().getItems().size(); i++){
                System.out.println("---------------------" + client.namespaces().list().getItems().get(i));
                namespace_list.add(client.namespaces().list().getItems().get(i));
            }
        }
        client.close();
        for(int i=0; i<namespace_list.size(); i++){
            endstr += namespace_list.get(i).toString();
        }
        return endstr;
    }

    // get pod
    public String getPod(String namespace, String podName){
        List pod_list = new ArrayList();
        String endstr = "";
        if(namespace != null){
            List podlist = client.pods().inNamespace(namespace).list().getItems();
            if(podName !=null){
                for(int i=0; i<podlist.size(); i++){
                    if(podlist.get(i).toString().split("selfLink=/api/v1/")[0].contains(podName)) {
                        pod_list.add(podlist.get(i));
                    }
                }
            }else {
                for (int i = 0; i < podlist.size(); i++) {
                    pod_list.add(podlist.get(i));
                }
            }
        }else{
            List podlist = client.pods().inNamespace("default").list().getItems();
            if(podName !=null){
                for(int i=0; i<podlist.size(); i++){
                    if(podlist.get(i).toString().split("selfLink=/api/v1/")[0].contains(podName)) {
                        pod_list.add(podlist.get(i));
                    }
                }
            }else {
                for (int i = 0; i < podlist.size(); i++) {
                    pod_list.add(podlist.get(i));
                }
            }
        }
        client.close();
        for(int i=0; i<pod_list.size(); i++){
            endstr += pod_list.get(i).toString();
        }
        return endstr;
    }

    //get RC
    public String getRC(String namespace, String RcName){
        List rc_list = new ArrayList();
        String endstr = "";
        if(namespace != null){
            List rcList = client.replicationControllers().inNamespace(namespace).list().getItems();
            if(RcName !=null){
                for(int i=0; i<rcList.size(); i++){
                    if(rcList.get(i).toString().split("selfLink=/api/v1/")[0].contains(RcName)) {
                        rc_list.add(rcList.get(i));
                    }
                }
            }else {
                for (int i = 0; i < rcList.size(); i++) {
                    rc_list.add(rcList.get(i));
                }
            }
        }else{
            List rcList = client.replicationControllers().inNamespace("default").list().getItems();
            if(RcName !=null){
                for(int i=0; i<rcList.size(); i++){
                    if(rcList.get(i).toString().split("selfLink=/api/v1/")[0].contains(RcName)) {
                        rc_list.add(rcList.get(i));
                    }
                }
            }else {
                for (int i = 0; i < rcList.size(); i++) {
                    rc_list.add(rcList.get(i));
                }
            }
        }
        client.close();
        for(int i=0; i<rc_list.size(); i++){
            endstr += rc_list.get(i).toString();
        }
        return endstr;
    }

    // get Service
    public String getservice(String namespace, String ServieName){
        List service_list = new ArrayList();
        String endstr= "";
        if(namespace != null){
            List servicelist = client.services().inNamespace(namespace).list().getItems();
            if(ServieName !=null){
                for(int i=0; i<servicelist.size(); i++){
                    if(servicelist.get(i).toString().split("selfLink=/api/v1/")[0].contains(ServieName)) {
                        service_list.add(servicelist.get(i));
                    }
                }
            }else {
                for (int i = 0; i < servicelist.size(); i++) {
                    service_list.add(servicelist.get(i));
                }
            }
        }else{
            List servicelist = client.services().inNamespace("default").list().getItems();
            if(ServieName !=null){
                for(int i=0; i<servicelist.size(); i++){
                    if(servicelist.get(i).toString().split("selfLink=/api/v1/")[0].contains(ServieName)) {
                        service_list.add(servicelist.get(i));
                    }
                }
            }else {
                for (int i = 0; i < servicelist.size(); i++) {
                    service_list.add(servicelist.get(i));
                }
            }
        }
        client.close();
        for(int i=0; i<service_list.size(); i++){
            endstr += service_list.get(i).toString();
        }
        return endstr;
    }



    // delete namespace
    public String deleteNamespace(String namespaceName){

        try {
            Boolean ac = client.namespaces().withName(namespaceName).delete();
            if(ac == true) {
                logger.info(" the namespaceName delete is ok!!");
                client.close();
                return  " the namespaceName " + namespaceName + " delete is ok!!\n";
            } else {
                logger.info(" the namespaceName delete is failed!!");
                return  " the namespaceName " + namespaceName + " delete is failed\n!!";
            }
        } catch (KubernetesClientException e){
            logger.info(" the namespaceName delete is failed!!");
            client.close();
            return " the namespaceName " + namespaceName + " delete is failed!!\n";
        }
//        client.close();
    }


    // delete pod
    public String deletePod(String namespace, String PodName){
        if(namespace != null){
            if(PodName != null){
                Boolean en = client.pods().inNamespace(namespace).withName(PodName).delete();
                if(en == true){
                    logger.info(" the pod delete is ok!!");
                    client.close();
                    return " the pod " + PodName +" delete is ok!!\n";
                }else{
                    logger.info(" the pod delete is failed!!");
                    client.close();
                    return " the pod " + PodName +" delete is failed!!\n";
                }
            }else{
                Boolean en = client.pods().inNamespace(namespace).delete();
                if(en == true){
                    logger.info(" the pod delete is ok!!");
                    client.close();
                    return " the podList  delete is ok!!\n";
                }else{
                    logger.info(" the pod delete is failed!!");
                    client.close();
                    return " the podList  delete is failed!!\n";
                }
            }
        }else {
            if(PodName != null){
                Boolean en = client.pods().inNamespace("default").withName(PodName).delete();
                if(en == true){
                    logger.info(" the pod delete is ok!!");
                    client.close();
                    return " the pod " + PodName +" delete is ok!!\n";
                }else{
                    logger.info(" the pod delete is failed!!");
                    client.close();
                    return " the pod " + PodName +" delete is failed!!\n";
                }
            }else{
                Boolean en = client.pods().inNamespace("default").delete();
                if(en == true){
                    logger.info(" the pod delete is ok!!");
                    client.close();
                    return " the podList  delete is ok!!\n";
                }else{
                    logger.info(" the pod delete is failed!!");
                    client.close();
                    return " the podList  delete is failed!!\n";
                }
            }

        }
//        client.close();
    }

    // delete rc
    public String deleteRc(String namespace, String RcName){
        if(namespace != null){
            if(RcName != null){
                try {
                    Boolean en = client.replicationControllers().inNamespace(namespace).withName(RcName).delete();
                    if (en == true) {
                        logger.info(" the RC delete is ok!!");
                        client.close();
                        return " the RC " + RcName +" delete is ok!!\n";
                    } else {
                        logger.info(" the RC delete is failed!!");
                        client.close();
                        return " the RC " + RcName +" delete is failed!!\n";

                    }
                }catch (KubernetesClientException e){
                    logger.info(" the RC delete is failed!!" + e);
                    client.close();
                    return " the RC " + RcName +" delete is failed!!\n";
                }
            }else{
                try {
                    Boolean en = client.replicationControllers().inNamespace(namespace).delete();
                    if (en == true) {
                        logger.info(" the RC delete is ok!!");
                        client.close();
                        return " the RCList  delete is ok!!\n";
                    } else {
                        logger.info(" the RC delete is failed!!");
                        client.close();
                        return " the RCList  delete is failed!!\n";
                    }
                } catch (KubernetesClientException e){
                    logger.info(" the RC delete is failed!!" + e);
                    client.close();
                    return " the RCList  delete is failed!!\n";
                }
            }
//            return  "aa";
        }else {
            if(RcName != null){
                try {
                    Boolean en = client.replicationControllers().inNamespace("default").withName(RcName).delete();
                    if (en == true) {
                        logger.info(" the RC delete is ok!!");
                        client.close();
                        return " the RC "+ RcName+ " delete is ok!!\n";
                    } else {
                        logger.info(" the RC delete is failed!!");
                        client.close();
                        return " the RC "+ RcName+ " delete is failed!!\n";
                    }
                } catch (KubernetesClientException e){
                    logger.info(" the RC delete is failed!!" + e);
                    client.close();
                    return " the RC "+ RcName+ " delete is failed!!\n";
                }
            }else {
                try {
                    Boolean en = client.replicationControllers().inNamespace("default").delete();
                    if (en == true) {
                        logger.info(" the RC delete is ok!!");
                        client.close();
                        return " the RcList delete is ok!!\n";
                    } else {
                        logger.info(" the RC delete is failed!!");
                        client.close();
                        return " the RcList delete is failed!!\n";
                    }
                }catch (KubernetesClientException e){
                    logger.info(" the RC delete is failed!!" + e);
                    client.close();
                    return " the RcList delete is failed!!\n";
                }
            }
        }


    }


    // delete service
    public String deleteService(String namespace, String ServiceName){
        if(namespace != null){
            if(ServiceName != null){
                Boolean en = client.services().inNamespace(namespace).withName(ServiceName).delete();
                if(en == true){
                    logger.info(" the Service delete is ok!!");
                    client.close();
                    return "the Service " +ServiceName+ " delete is ok\n!!";
                }else{
                    logger.info(" the Service delete is failed!!");
                    client.close();
                    return "the Service " +ServiceName+ " delete is failed\n!!";
                }
            }else{
                Boolean en = client.services().inNamespace(namespace).delete();
                System.out.println("-----------------11111111111" + en);
                if(en == true){
                    logger.info(" the Service delete is ok!!");
                    client.close();
                    return "the ServiceList delete is ok!!\n";
                }else{
                    logger.info(" the Service delete is failed!!");
                    client.close();
                    return "the ServiceList delete is failed!!\n";
                }
            }
        }else {
            if(ServiceName != null){
                Boolean en = client.services().inNamespace("default").withName(ServiceName).delete();
                if(en == true){
                    logger.info(" the Service delete is ok!!");
                    client.close();
                    return "the Service " + ServiceName +" delete is ok!!\n";
                }else{
                    logger.info(" the Service delete is failed!!");
                    client.close();
                    return "the Service " + ServiceName +" delete is failed\n!!";
                }
            }else{
                Boolean en = client.services().inNamespace("default").delete();
                if(en == true){
                    logger.info(" the Service delete is ok!!");
                    client.close();
                    return "the ServiceList delete is ok!!\n";
                }else{
                    logger.info(" the Service delete is failed!!");
                    client.close();
                    return "the ServiceList delete is failed!!\n";
                }
            }
        }
//        client.close();
    }

    public String RcScala(String namespace, String RcName, int scalaNum){
        if(namespace == null) {
            try {
                client.replicationControllers().inNamespace("default").withName(RcName).scale(scalaNum);
                return "the Rc scala is ok, the instance is " + scalaNum  +"\n";
            } catch (KubernetesClientException e){
                logger.info("the RC scala id failed " + e);
                client.close();
                return "the Rc scala is failed\n";
            }
        } else{
            try {
                client.replicationControllers().inNamespace(namespace).withName(RcName).scale(scalaNum);
                client.close();
                return "the Rc scala is ok, the instance is " + scalaNum  +"\n";

            }catch (KubernetesClientException e){
                logger.info("the RC scala id failed " + e);
                client.close();
                return "the Rc scala is failed\n";
            }
        }
//        client.close();
    }


}
