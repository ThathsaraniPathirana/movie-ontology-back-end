package ontologie.demo.ws;

import org.apache.jena.ontology.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class Ontologie {

    @RequestMapping(value = "/ontologies",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getontologies() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            Iterator ontologiesIter = model.listOntologies();
            while (ontologiesIter.hasNext()) {
                Ontology ontology = (Ontology) ontologiesIter.next();

                JSONObject obj = new JSONObject();
                obj.put("name",ontology.getLocalName());
                obj.put("uri",ontology.getURI());
                list.add(obj);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/classesList",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getClasses() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            Iterator classIter = model.listClasses();
            while (classIter.hasNext()) {
                OntClass ontClass = (OntClass) classIter.next();
                JSONObject obj = new JSONObject();
                obj.put("name",ontClass.getLocalName());
                obj.put("uri",ontClass.getURI());
                list.add(obj);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //error
    @RequestMapping(value = "/subClasses",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getSubClasses(@RequestParam("classname") String className) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String classURI = "http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#".concat(className);
            System.out.println(classURI);
            OntClass personne = model.getOntClass(classURI );
            Iterator subIter = personne.listSubClasses();
            while (subIter.hasNext()) {
                OntClass sub = (OntClass) subIter.next();
                    JSONObject obj = new JSONObject();
                    obj.put("URI",sub.getURI());
                    list.add(obj);


            }

            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/Individuals",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getIndividus() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            Iterator individus = model.listIndividuals();
            while (individus.hasNext()) {
                Individual   sub = (Individual) individus.next();
                JSONObject obj = new JSONObject();
                obj.put("name",sub.getLocalName());
                obj.put("uri",sub.getURI());
                list.add(obj);

            }

            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/superClasses",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getSuperClasses(@RequestParam("classname") String className) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String classURI = "http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#".concat(className);
            System.out.println(classURI);
            OntClass personne = model.getOntClass(classURI );
            Iterator subIter = personne.listSuperClasses();
            while (subIter.hasNext()) {
                OntClass sub = (OntClass) subIter.next();
                JSONObject obj = new JSONObject();
                obj.put("URI",sub.getURI());
                list.add(obj);


            }

            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/getClassProperty",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getClasProperty(@RequestParam("classname") String className) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String classURI = "http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#".concat(className);

            OntClass ontClass = model.getOntClass(classURI );
            Iterator subIter = ontClass.listDeclaredProperties();
            while (subIter.hasNext()) {
                OntProperty property = (OntProperty) subIter.next();
                JSONObject obj = new JSONObject();
                obj.put("propertyName",property.getLocalName());

                    obj.put("propertyType",property.getRDFType().getLocalName());

                if(property.getDomain()!=null)
                    obj.put("domain", property.getDomain().getLocalName());
                if(property.getRange()!=null)
                    obj.put("range",property.getRange().getLocalName());

                list.add(obj);


            }

            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/equivClasses",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getequivClasses(@RequestParam("classname") String className) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String classURI = "http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#".concat(className);
            System.out.println(classURI);
            OntClass personne = model.getOntClass(classURI );
            Iterator subIter = personne.listEquivalentClasses();
            while (subIter.hasNext()) {
                OntClass sub = (OntClass) subIter.next();
                JSONObject obj = new JSONObject();
                obj.put("URI",sub.getURI());
                list.add(obj);


            }

            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/Instances",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> getInstancesClasses(@RequestParam("classname") String className) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String classURI = "http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#".concat(className);
            System.out.println(classURI);
            OntClass personne = model.getOntClass(classURI );
            Iterator subIter = personne.listInstances();
            while (subIter.hasNext()) {
                Individual   sub = (Individual) subIter.next();
                JSONObject obj = new JSONObject();
                obj.put("name",sub.getLocalName());
                obj.put("uri",sub.getURI());
                list.add(obj);

            }

            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/isHierarchyRoot",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<JSONObject> isHirarchieroot(@RequestParam("classname") String className) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String classURI = "http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#".concat(className);
            System.out.println(classURI);
            OntClass personne = model.getOntClass(classURI );

          if (personne != null){
              JSONObject obj = new JSONObject();
              if (personne.isHierarchyRoot()){
                  obj.put("isroot","true");
              }else {
                  obj.put("isroot","false");
              }

              list.add(obj);

          }



            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getActors",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> query() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?a " +
                    "WHERE { ?a film:isActorIn ?z}";
            Query query = QueryFactory.create(sprql);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
           int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("a").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getActresses",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getActress() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?actress " +
                    "WHERE { ?actress film:isActressIn ?z}";
            Query query = QueryFactory.create(sprql2);
            QueryExecution execution1 = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = execution1.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("actress").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getDirectors",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getDirectors() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sparql3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?director " +
                    "WHERE { ?director film:isDirectorOf ?z}";
            Query query = QueryFactory.create(sparql3);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("director").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getArtDirectors",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getArtDirectors() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?artDirector " +
                    "WHERE { ?artDirector film:isArtDirectorOf ?z}";
            Query query = QueryFactory.create(sprql);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("artDirector").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getEditors",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getEditors() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?editor " +
                    "WHERE { ?editor film:isEditorIn ?z}";
            Query query = QueryFactory.create(sprql);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("editor").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getCostumeDesigners",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getCostumeDesigners() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?designer " +
                    "WHERE { ?designer film:isCostumeDesignerIn ?z}";
            Query query = QueryFactory.create(sprql);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("designer").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getWriters",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getWriters() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?w " +
                    "WHERE { ?w film:isWriterOf ?z}";
            Query query = QueryFactory.create(sprql);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("w").toString().substring(78));
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getGenres",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getGenres() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?genre " +
                    "WHERE { ?genre film:isGenreOf ?z}";
            Query query = QueryFactory.create(sprql);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("genre").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getFilteredMovies",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getFilteredMovies(@RequestBody MoviePOJO moviePOJO) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        String actor = moviePOJO.getActor();
        String actress = moviePOJO.getActress();
        String editor = moviePOJO.getEditor();
        String director = moviePOJO.getDirector();
        String artDirector = moviePOJO.getArtDirector();
        String costumeDesigner = moviePOJO.getCostumeDesigner();
        String writer = moviePOJO.getWriter();

        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "SELECT ?m " +
                    "WHERE { ?m film:hasActor film:"+actor+";" +
                    "           film:hasActress film:"+actress+";" +
                    "           film:hasEditor film:"+editor+";" +
                    "           film:hasDirector film:"+director+";" +
                    "           film:hasArtDirector film:"+artDirector+";" +
                    "           film:hasCostumeDesigner film:"+costumeDesigner+";" +
                    "           film:hasWriter film:"+writer+"" +
                    "       }";
            Query query = QueryFactory.create(sprql);
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("m").toString().split("#")[1]);
                list.add(obj);
            }
            System.out.println(x);
            return list;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
