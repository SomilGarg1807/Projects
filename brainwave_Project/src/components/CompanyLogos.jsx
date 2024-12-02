import { companyLogos } from "../constants";

const CompanyLogos = ({ className }) => {
  return (
    <div className={className}>
      <h5 className="tagline mb-6 text-center text-n-1/50">
        Helping people create beautiful content at
      </h5>
      <ul className="flex">
        {companyLogos.map((logo, index) => (
          <li
            className="flex-1 justify-center items-center h-[8.5rem]"
            key={index}
          >
            <img src={logo} alt={logo} height={24} width={100} />
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CompanyLogos;
