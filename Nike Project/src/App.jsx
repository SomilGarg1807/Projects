import { CustomerReviews, SuperQuality,Subscribee,SpecialOffer,Services,PopularProducts,Hero,Footer} from "./sections";
import Navv from "./components/Navv";

const App= () => {
  return(
  <main className="relative">
    <Navv/>

    <section className="xl:padding-l wide:padding-r padding-b">
      <Hero/>
    </section>

    <section className="padding">
      <PopularProducts/>
    </section>

    <section className="padding">
      <SuperQuality/>
    </section>

    <section className="padding-x py-10">
      <Services/>
    </section>

    <section className="padding">
      <SpecialOffer/>
    </section>

    <section className="bg-pale-blue">
      <CustomerReviews/>
    </section>

    <section className="padding-x sm:py-32 py-16 w-full">
      <Subscribee/>
    </section>

    <section className="bg-black padding-t padding-x pb-8">
      <Footer/>
    </section>

  </main>
  );
};

export default App;