package ontologie.demo.ws;

import org.apache.jena.ontology.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class MovieOntologyController {

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
    public   List<JSONObject> getIndividuals() {
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
            OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);
            String classURI = "http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#".concat(className);
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
    public   List<JSONObject> getClassProperty(@RequestParam("classname") String className) {
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
            System.out.println(sprql);
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

            String sprql2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?actress \n" +
                    "WHERE { \n ?actress film:isActressIn ?z \n}";

            System.out.println(sprql2);
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

            String sparql3 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?director \n" +
                    "WHERE { \n?director film:isDirectorOf ?z\n}";
            System.out.println(sparql3);
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

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?artDirector \n" +
                    "WHERE {\n ?artDirector film:isArtDirectorOf ?z\n}";
            System.out.println(sprql);
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

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?editor \n" +
                    "WHERE {\n?editor film:isEditorIn ?z\n}";
            System.out.println(sprql);
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

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?designer \n" +
                    "WHERE { \n?designer film:isCostumeDesignerIn ?z\n}";
            System.out.println(sprql);
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

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?w \n" +
                    "WHERE { \n?w film:isWriterOf ?z\n}";
            System.out.println(sprql);
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

            String sprql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "SELECT DISTINCT ?genre\n" +
                    "\tWHERE { ?movie  film:belongsToGenre ?genre}\n";
            System.out.println(sprql);
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
    @RequestMapping(value = "/getCountry",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getCountry() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String countrySparql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "SELECT DISTINCT ?country\n" +
                    "\tWHERE { ?movie film:hasReleasingCountry ?country}\n";
            System.out.println(countrySparql);
            Query query = QueryFactory.create(countrySparql);
            QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = queryExecution.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("country").toString().split("#")[1]);
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
    @RequestMapping(value = "/getAwards",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getAwards() {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";
        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            String countrySparql = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "SELECT DISTINCT ?award\n" +
                    "\tWHERE { ?award film:isAwardOf ?movie}\n";
            System.out.println(countrySparql);
            Query query = QueryFactory.create(countrySparql);
            QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = queryExecution.execSelect();
            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("award").toString().split("#")[1]);
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

            String queryPrefixes = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n";
            String querySelection = "SELECT ?m\n";
            String queryWhereClause = "WHERE {\n";
            String queryEnd = "}";

            String queryActor;
            String queryActress;
            String queryEditor;
            String queryDirector;
            String queryArtDirector;
            String queryCostumeDesigner;
            String queryWriter;

            if (actor.equals("")) {
                queryActor = "?m film:hasActor ?a.\n";
            } else {
                queryActor = "?m film:hasActor film:"+actor+".\n";
            }

            if (actress.equals("")) {
                queryActress = "?m film:hasActress ?b.\n";
            } else {
                queryActress = "?m film:hasActress film:"+actress+".\n";
            }

            if (editor.equals("")) {
                queryEditor = "?m film:hasEditor ?c.\n";
            } else {
                queryEditor = "?m film:hasEditor film:"+editor+".\n";
            }

            if (director.equals("")) {
                queryDirector = "?m film:hasDirector ?d.\n";
            } else {
                queryDirector = "?m film:hasDirector film:"+director+".\n";
            }

            if (artDirector.equals("")) {
                queryArtDirector = "?m film:hasArtDirector ?e.\n";
            } else {
                queryArtDirector = "?m film:hasArtDirector film:"+artDirector+".\n";
            }

            if (costumeDesigner.equals("")) {
                queryCostumeDesigner = "?m film:hasCostumeDesigner ?f.\n";
            } else {
                queryCostumeDesigner = "?m film:hasCostumeDesigner film:"+costumeDesigner+".\n";
            }

            if (writer.equals("")) {
                queryWriter = "?m film:hasWriter ?g.\n";
            } else {
                queryWriter = "?m film:hasWriter film:"+writer+".\n";
            }

            String finalQuery = queryPrefixes + querySelection + queryWhereClause +
                    queryActor +
                    queryActress +
                    queryDirector +
                    queryEditor +
                    queryArtDirector +
                    queryCostumeDesigner +
                    queryWriter +
                    queryEnd;
            System.out.println(finalQuery);

            Query query = QueryFactory.create(finalQuery);
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

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/getMovieDetails",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getMovieDetails(@RequestBody MoviePOJO moviePOJO) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";

        String genre = moviePOJO.getGenre();
        String award = moviePOJO.getAward();
        String country = moviePOJO.getCountry();

        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory
                    .createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?movie\n" +
                    "WHERE {");

            if (!genre.equals("")) {
                stringBuilder.append("?movie film:belongsToGenre film:").append(genre).append(".").append("\n");
            }

            if (!award.equals("")) {
                stringBuilder.append("film:").append(award).append(" film:isAwardOf ").append("?movie").append(".").append("\n");
            }
            if (!country.equals("")) {
                stringBuilder.append("?movie film:hasReleasingCountry film:").append(country).append(".").append("\n");
            }

            stringBuilder.append("}");

            System.out.println(stringBuilder);

            Query query = QueryFactory.create(String.valueOf(stringBuilder));
            QueryExecution qe = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = qe.execSelect();

            int x=0;
            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("object",solution.get("movie").toString().split("#")[1]);
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
    @RequestMapping(value = "/getSelectedMovieDetails",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> getSelectedMovieDetails(@RequestBody MoviePOJO moviePOJO) {
        List<JSONObject> list=new ArrayList<>();
        String fileName = "movie_ontology.owl";

        String name = moviePOJO.getName();

        try {
            File file = new File(fileName);
            FileReader reader = new FileReader(file);
            OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
            model.read(reader,null);

            StringBuilder builder = new StringBuilder();

            builder.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX film: <http://www.semanticweb.org/yachithasandaruwan/ontologies/2019/6/film_ontology#>\n" +
                    "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "SELECT ?actor ?actress ?artDirector ?director ?editor ?costumeDesigner ?writer\n" +
                    "WHERE {");

            if (!name.equals("")) {
                builder.append("film:").append(name).append(" film:hasActor ?actor").append(".").append("\n");
                builder.append("film:").append(name).append(" film:hasActress ?actress").append(".").append("\n");
                builder.append("film:").append(name).append(" film:hasArtDirector ?artDirector").append(".").append("\n");
                builder.append("film:").append(name).append(" film:hasDirector ?director").append(".").append("\n");
                builder.append("film:").append(name).append(" film:hasEditor ?editor").append(".").append("\n");
                builder.append("film:").append(name).append(" film:hasCostumeDesigner ?costumeDesigner").append(".").append("\n");
                builder.append("film:").append(name).append(" film:hasWriter ?writer").append(".").append("\n");
            }

            builder.append("}");

            System.out.println(builder);

            Query query = QueryFactory.create(String.valueOf(builder));
            QueryExecution queryExecution = QueryExecutionFactory.create(query, model);
            ResultSet resultSet = queryExecution.execSelect();
            int x=0;

            while (resultSet.hasNext()) {
                x++;
                JSONObject obj = new JSONObject();
                QuerySolution solution = resultSet.nextSolution();
                obj.put("actor",solution.get("actor").toString().split("#")[1]);
                obj.put("actress",solution.get("actress").toString().split("#")[1]);
                obj.put("artDirector",solution.get("artDirector").toString().split("#")[1]);
                obj.put("director",solution.get("director").toString().split("#")[1]);
                obj.put("editor",solution.get("editor").toString().split("#")[1]);
                obj.put("costumeDesigner",solution.get("costumeDesigner").toString().split("#")[1]);
                obj.put("writer",solution.get("writer").toString().split("#")[1]);
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
